package edu.unam.ecomarket.controller;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.modelo.payment.MpNotifyDTO;
import edu.unam.ecomarket.services.MercadoPagoService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador para manejar la integración con MercadoPago.
 * 
 * <p>
 * Este controlador se encarga de gestionar la creación de preferencias de pago,
 * redirigir a la pasarela de MercadoPago y recibir notificaciones de pagos realizados.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
@RequestMapping("/api/v1/mercadopago")
public class MercadoPagoController {

    /**
     * Servicio para interactuar con las APIs de MercadoPago.
     */
    @Autowired
    private MercadoPagoService mercadoPagoService;

    /**
     * URL base de la aplicación, definida en las propiedades de configuración.
     */
    @Value("${app.base.url}")
    private String baseUrl;

    /**
     * Objeto para gestionar la sesión actual.
     */
    @Autowired
    private HttpSession session;

    /**
     * Redirige al usuario a la preferencia de pago generada en MercadoPago.
     * 
     * <p>
     * Este método crea una preferencia de pago utilizando el servicio de MercadoPago,
     * define las URLs de redirección en caso de éxito, pendiente o fallo, y redirige
     * al usuario a la URL de la preferencia generada.
     * </p>
     * 
     * @param redirectAttributes Atributos para redirigir al usuario.
     * @return Una redirección a la URL de la preferencia generada o a una página de error.
     */
    @PostMapping("/preference")
    public String redirigirPreferencia(RedirectAttributes redirectAttributes) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        MetodoEnvio envio = (MetodoEnvio) session.getAttribute("envio");
        try {
            // Crear la URL de los Back URLs
            BacksUrlDTO backsUrl = new BacksUrlDTO();
            backsUrl.setSuccess(baseUrl + "/response/success");
            backsUrl.setPending(baseUrl + "/response/pending");
            backsUrl.setFailure(baseUrl + "/response/failed");

            // Generar la URL de la preferencia
            String preferenceUrl = this.mercadoPagoService.createPreference(
                    backsUrl, baseUrl + "/api/v1/mercadopago/notify", envio);

            // Almacenar el envío en la sesión para referencia futura
            session.setAttribute("envio", envio);

            // Redirigir a la URL de la preferencia
            return "redirect:" + preferenceUrl;
        } catch (MPException | MPApiException e) {
            logger.error("Error creando preferencia de pago en MercadoPago", e);
            return "errorPageCliente"; // Redirigir a una página de error para el cliente
        } catch (Exception e) {
            logger.error("Error inesperado creando preferencia de pago", e);
            return "errorPageCliente"; // Redirigir a una página de error para el cliente
        }
    }

    /**
     * Endpoint para recibir notificaciones de pago de MercadoPago.
     * 
     * <p>
     * Este método recibe notificaciones enviadas por MercadoPago sobre el estado de un pago.
     * Actualmente, solo registra la notificación en el log y responde con un estado 200 OK
     * para confirmar la recepción.
     * </p>
     * 
     * @param mpNotify Objeto que contiene los datos de la notificación enviada por MercadoPago.
     * @return Una respuesta HTTP 200 OK para confirmar la recepción de la notificación.
     */
    @PostMapping(value = "notify")
    public ResponseEntity<Void> notifyPay(@RequestBody MpNotifyDTO mpNotify) {
        // Log de la notificación de pago
        System.out.println("Notificación de pago recibida: " + mpNotify.toString());

        return ResponseEntity.ok().build();
    }
}
