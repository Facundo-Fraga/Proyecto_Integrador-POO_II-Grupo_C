package edu.unam.ecomarket.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.repositories.CarritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio que gestiona la integración con MercadoPago para la creación de
 * preferencias de pago.
 * 
 * <p>
 * Proporciona un método para generar una preferencia de pago basada en los
 * productos del carrito y el costo del envío, y devuelve una URL de redirección
 * para iniciar el proceso de pago.
 * </p>
 * 
 * <p>
 * Se apoya en la biblioteca oficial de MercadoPago para interactuar con la API
 * y en el repositorio {@link CarritoRepository} para acceder a los productos en el carrito.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Service
public class MercadoPagoService {

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoService.class);

    @Value("${meli.accesToken}")
    private String accesToken;

    @Autowired
    private CarritoRepository carritoRepository;

    /**
     * Crea una preferencia de pago en MercadoPago basada en los productos del carrito
     * y el costo del envío.
     * 
     * @param backsUrl        URLs de redirección después de un intento de pago
     *                        (éxito, pendiente, fallo).
     * @param notificationUrl URL para recibir notificaciones de estado de pago.
     * @param envio           Método de envío que contiene el costo.
     * @return Una URL para redirigir al usuario a iniciar el proceso de pago.
     * @throws MPException    Si ocurre un error al comunicarse con la API de MercadoPago.
     * @throws MPApiException Si ocurre un error relacionado con la API de MercadoPago.
     * @throws IllegalArgumentException Si no hay suficientes datos para crear la preferencia.
     */
    public String createPreference(BacksUrlDTO backsUrl, String notificationUrl, MetodoEnvio envio)
            throws MPException, MPApiException {

        try {
            // Configurar el token de acceso de MercadoPago
            MercadoPagoConfig.setAccessToken(accesToken);

            // Validar datos de entrada
            if (envio == null || carritoRepository.obtenerProductosEnCarrito().isEmpty()) {
                throw new IllegalArgumentException("Datos insuficientes para crear la preferencia.");
            }

            // Obtener productos del carrito
            Map<Producto, Integer> productosEnCarrito = carritoRepository.obtenerProductosEnCarrito();
            List<PreferenceItemRequest> items = new ArrayList<>();

            // Crear un ítem para cada producto
            for (Map.Entry<Producto, Integer> entry : productosEnCarrito.entrySet()) {
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();

                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .title(producto.getNombre())
                        .quantity(cantidad)
                        .currencyId("ARS")
                        .unitPrice(BigDecimal.valueOf(producto.getPrecioFinal()))
                        .build();

                items.add(itemRequest);
            }

            // Añadir el costo del envío como ítem adicional
            PreferenceItemRequest envioItem = PreferenceItemRequest.builder()
                    .title("Costo de Envío")
                    .quantity(1)
                    .currencyId("ARS")
                    .unitPrice(BigDecimal.valueOf(envio.getTarifaInterna()))
                    .build();
            items.add(envioItem);

            // Configurar las URLs de redirección
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success(backsUrl.getSuccess())
                    .pending(backsUrl.getPending())
                    .failure(backsUrl.getFailure())
                    .build();

            // Crear la solicitud de preferencia
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .notificationUrl(notificationUrl)
                    .build();

            // Crear la preferencia de pago
            PreferenceClient preferenceClient = new PreferenceClient();
            Preference preference = preferenceClient.create(preferenceRequest);

            // Retornar la URL de inicio de pago
            return preference.getInitPoint();
        } catch (MPException | MPApiException ex) {
            logger.error("Error al crear la preferencia: {}", ex.getMessage());
            throw ex;
        }
    }
}
