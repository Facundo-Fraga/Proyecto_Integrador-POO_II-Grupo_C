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

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.repositories.CarritoRepository;

@Service
public class MercadoPagoService {

    @Value("${meli.accesToken}")
    private String accesToken;

    @Autowired
    private CarritoRepository carritoRepository;

    // Método para crear la preferencia de pago
    public String createPreference(BacksUrlDTO backsUrl, String notificationUrl) throws MPException, MPApiException {

        // Configura el token de acceso para MercadoPago
        MercadoPagoConfig.setAccessToken(accesToken);

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
    }
}