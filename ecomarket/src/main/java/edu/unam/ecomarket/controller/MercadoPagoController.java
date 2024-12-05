package edu.unam.ecomarket.controller;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.modelo.payment.MpNotifyDTO;
import edu.unam.ecomarket.services.MercadoPagoService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

@Controller
@RequestMapping("/api/v1/mercadopago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;
    @Value("${app.base.url}")
    private String baseUrl;
    @Autowired
    private HttpSession session;

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

            String preferenceUrl;

            // Llamada al servicio para crear la preferencia de pago
            preferenceUrl = this.mercadoPagoService.createPreference(backsUrl,
                    baseUrl + "/api/v1/mercadopago/notify", envio);
            // Almacenar el objeto en la sesión o incluir un ID como referencia
        session.setAttribute("envio", envio);
            // Redirigir a la URL de la preferencia
            return "redirect:" + preferenceUrl;
        } catch (MPException | MPApiException e) {
            // Capturamos excepciones específicas de MercadoPago
            logger.error("Error creando preferencia de pago en MercadoPago", e);

            return "errorPageCliente"; // Redirigir a una página de error
        } catch (Exception e) {
            // Capturamos cualquier otra excepción
            logger.error("Error inesperado creando preferencia de pago", e);

            return "errorPageCliente"; // Redirigir a una página de error
        }
    }

    // Endpoint para recibir la notificación del pago
    @PostMapping(value = "notify")
    public ResponseEntity<Void> notifyPay(@RequestBody MpNotifyDTO mpNotify) {
        // Log de la notificación de pago
        System.out.println("Notificación de pago recibida: " + mpNotify.toString());
        

        // Aquí puedes manejar la notificación del pago (actualizar estado, etc.)
        // Devuelve 200 OK para confirmar la recepción de la notificación
        return ResponseEntity.ok().build();
    }

}
