package edu.unam.ecomarket.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.PaymentResponseController;
import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.DetallePedido;
import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.Pago;
import edu.unam.ecomarket.modelo.Pedido;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.CarritoService;
import edu.unam.ecomarket.services.PedidoService;
import edu.unam.ecomarket.services.UsuarioService;

@WebMvcTest(PaymentResponseController.class)
public class PaymentResponseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private PedidoService pedidoService;

    private Cliente clienteMock;
    private MetodoEnvio envioMock;
    private Map<Producto, Integer> productosCarritoMock;
    private Pedido pedidoMock;

    private MockHttpSession session;

    // Subclase concreta para Pedido
    public static class PedidoConcreto extends Pedido {
        public PedidoConcreto(Long id, MetodoEnvio metodoEnvio, Pago pago, List<DetallePedido> detallesPedido, Cliente cliente) {
            super(metodoEnvio, pago, detallesPedido, cliente);
        }
    }

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession(); // Mock de HttpSession

        clienteMock = new Cliente();
        clienteMock.setNombre("Cliente de Prueba");

        envioMock = mock(MetodoEnvio.class);

        productosCarritoMock = Map.of(mock(Producto.class), 2); // Producto mockeado con cantidad

        List<DetallePedido> detallesMock = new ArrayList<>();
        Producto productoMock = mock(Producto.class);
        when(productoMock.getPrecioFinal()).thenReturn(50.0); // Mock del precio final
        detallesMock.add(new DetallePedido(2, productoMock));

        pedidoMock = new PedidoConcreto(1L, envioMock, mock(Pago.class), detallesMock, clienteMock);

        // Configurar la sesión
        session.setAttribute("envio", envioMock);

        // Configurar comportamiento de los mocks
        when(usuarioService.obtenerClienteEnSesion(any())).thenReturn(clienteMock);
        when(carritoService.obtenerProductosEnCarrito()).thenReturn(productosCarritoMock);
        when(pedidoService.crearPedido(any(Cliente.class), anyMap(), anyString(), anyString(), any(MetodoEnvio.class)))
                .thenReturn(pedidoMock);
    }

    @Test
    public void testPaymentSuccess() throws Exception {
        mockMvc.perform(get("/response/success")
                .session(session)
                .param("payment_id", "12345")
                .param("status", "approved")
                .param("external_reference", "ref123"))
                .andExpect(status().isOk())
                .andExpect(view().name("succesfulPayment"))
                .andExpect(model().attributeExists("pedido"))
                .andExpect(model().attribute("paymentId", "12345"))
                .andExpect(model().attribute("status", "approved"))
                .andExpect(model().attribute("externalReference", "ref123"));

        verify(carritoService, times(1)).vaciarCarrito();
        verify(pedidoService, times(1)).crearPedido(eq(clienteMock), eq(productosCarritoMock), eq("12345"), eq("approved"), eq(envioMock));
    }

    @Test
    public void testPaymentSuccessNoClienteEnSesion() throws Exception {
        when(usuarioService.obtenerClienteEnSesion(any())).thenReturn(null);

        mockMvc.perform(get("/response/success")
                .session(session)
                .param("payment_id", "12345")
                .param("status", "approved")
                .param("external_reference", "ref123"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPageCliente"))
                .andExpect(model().attributeExists("error"));

        verify(pedidoService, times(0)).crearPedido(any(), any(), any(), any(), any());
    }

    @Test
    public void testPaymentPending() throws Exception {
        mockMvc.perform(get("/response/pending")
                .param("payment_id", "12345")
                .param("status", "pending")
                .param("external_reference", "ref123"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentResponse"))
                .andExpect(model().attribute("title", "Pago Pendiente"))
                .andExpect(model().attribute("message", "Tu pago está pendiente. Por favor, espera la confirmación."))
                .andExpect(model().attribute("paymentId", "12345"))
                .andExpect(model().attribute("status", "pending"))
                .andExpect(model().attribute("externalReference", "ref123"));
    }

    @Test
    public void testPaymentFailed() throws Exception {
        mockMvc.perform(get("/response/failed")
                .param("payment_id", "12345")
                .param("status", "failed")
                .param("external_reference", "ref123"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentResponse"))
                .andExpect(model().attribute("title", "Pago Fallido"))
                .andExpect(model().attribute("message", "Lo sentimos, tu pago no pudo completarse."))
                .andExpect(model().attribute("paymentId", "12345"))
                .andExpect(model().attribute("status", "failed"))
                .andExpect(model().attribute("externalReference", "ref123"));
    }
}
