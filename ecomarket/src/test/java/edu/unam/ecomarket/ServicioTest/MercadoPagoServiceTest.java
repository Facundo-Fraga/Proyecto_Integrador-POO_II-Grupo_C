package edu.unam.ecomarket.ServicioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.repositories.CarritoRepository;
import edu.unam.ecomarket.services.MercadoPagoService;

class MercadoPagoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private PreferenceClient preferenceClient;

    @InjectMocks
    private MercadoPagoService mercadoPagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePreference() throws MPException, MPApiException {
        // Configurar mocks de productos y carrito
        Producto productoMock = mock(Producto.class);
        when(productoMock.getNombre()).thenReturn("Producto Test");
        when(productoMock.getPrecioFinal()).thenReturn(100.0);

        Map<Producto, Integer> productosEnCarrito = new HashMap<>();
        productosEnCarrito.put(productoMock, 2);
        when(carritoRepository.obtenerProductosEnCarrito()).thenReturn(productosEnCarrito);

        // Configurar mock de MetodoEnvio
        MetodoEnvio metodoEnvioMock = mock(MetodoEnvio.class);
        when(metodoEnvioMock.getTarifaInterna()).thenReturn(50.0);

        // Configurar BacksUrlDTO
        BacksUrlDTO backsUrlMock = new BacksUrlDTO();
        backsUrlMock.setSuccess("https://success.url");
        backsUrlMock.setPending("https://pending.url");
        backsUrlMock.setFailure("https://failure.url");

        // Crear un spy de MercadoPagoService
        MercadoPagoService mercadoPagoServiceSpy = spy(new MercadoPagoService());

        // Sobrescribir completamente el comportamiento del método
        doReturn("https://payment.url").when(mercadoPagoServiceSpy).createPreference(
                eq(backsUrlMock),
                eq("https://notification.url"),
                eq(metodoEnvioMock));

        // Ejecutar el método (sin llamadas reales a la API)
        String initPoint = mercadoPagoServiceSpy.createPreference(backsUrlMock, "https://notification.url",
                metodoEnvioMock);

        // Verificar resultados
        assertEquals("https://payment.url", initPoint);

        // Verificar interacción con carritoRepository - Error aqui debajo - TEST PENDIENTE
        verify(carritoRepository, times(1)).obtenerProductosEnCarrito();
    }

    @Test
    void testCreatePreferenceWithEmptyCart() {
        // Configurar carrito vacío
        when(carritoRepository.obtenerProductosEnCarrito()).thenReturn(new HashMap<>());

        // Configurar MetodoEnvio mock
        MetodoEnvio metodoEnvioMock = mock(MetodoEnvio.class);

        // Configurar BacksUrlDTO mock
        BacksUrlDTO backsUrlMock = mock(BacksUrlDTO.class);

        // Verificar que se lanza IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> mercadoPagoService.createPreference(backsUrlMock, "https://notification.url", metodoEnvioMock));

        verify(carritoRepository, times(1)).obtenerProductosEnCarrito();
    }
}
