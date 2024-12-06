package edu.unam.ecomarket.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.repositories.CarritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MercadoPagoService {

    // Declarar el logger
    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoService.class);

    @Value("${meli.accesToken}")
    private String accesToken;

    @Autowired
    private CarritoRepository carritoRepository;

    // Método para crear la preferencia de pago
    public String createPreference(BacksUrlDTO backsUrl, String notificationUrl, MetodoEnvio envio)
            throws MPException, MPApiException {

        try {
            MercadoPagoConfig.setAccessToken(accesToken);

            if (envio == null || carritoRepository.obtenerProductosEnCarrito().isEmpty()) {
                throw new IllegalArgumentException("Datos insuficientes para crear la preferencia.");
            }

            // Obtener los productos en el carrito
            Map<Producto, Integer> productosEnCarrito = carritoRepository.obtenerProductosEnCarrito();
            List<PreferenceItemRequest> items = new ArrayList<>();

            // Crear un ítem para cada producto en el carrito
            for (Map.Entry<Producto, Integer> entry : productosEnCarrito.entrySet()) {
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();

                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .title(producto.getNombre()) // Título del artículo (nombre del producto)
                        .quantity(cantidad) // Cantidad del artículo
                        .currencyId("ARS") // Moneda
                        .unitPrice(BigDecimal.valueOf(producto.getPrecioFinal())) // Precio unitario del producto
                        .build();

                items.add(itemRequest);
            }

            // Añadir el costo del envío como un ítem adicional
            PreferenceItemRequest envioItem = PreferenceItemRequest.builder()
                    .title("Costo de Envío")
                    .quantity(1)
                    .currencyId("ARS")
                    .unitPrice(BigDecimal.valueOf(envio.getTarifaInterna()))
                    .build();
            items.add(envioItem);

            // Crear los objetos necesarios para la preferencia
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success(backsUrl.getSuccess())
                    .pending(backsUrl.getPending())
                    .failure(backsUrl.getFailure())
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .notificationUrl(notificationUrl)
                    .build();

            // Crear la preferencia de pago
            PreferenceClient preferenceClient = new PreferenceClient();
            Preference preference = preferenceClient.create(preferenceRequest);

            // Retornar la URL de redirección para iniciar el pago
            return preference.getInitPoint();
        } catch (MPException | MPApiException ex) {
            // Registro del error
            logger.error("Error al crear la preferencia: {}", ex.getMessage());
            throw ex; // Relanzar para manejar en capas superiores
        }
    }
}
