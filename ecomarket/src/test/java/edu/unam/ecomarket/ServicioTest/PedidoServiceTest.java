package edu.unam.ecomarket.ServicioTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.DetallePedido;
import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.PagoMercadoPago;
import edu.unam.ecomarket.modelo.Pedido;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.DetallePedidoRepository;
import edu.unam.ecomarket.repositories.PagoRepository;
import edu.unam.ecomarket.repositories.PedidoRepository;
import edu.unam.ecomarket.repositories.UsuarioRepository;
import edu.unam.ecomarket.services.PedidoService;

class PedidoServiceTest {

    // Subclase concreta de Producto para pruebas
    static class ProductoConcreto extends Producto {
        public ProductoConcreto(String nombre, String descripcion, double precioBase) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precioBase = precioBase;
        }
    }

    // Subclase concreta de MetodoEnvio para pruebas
    static class MetodoEnvioConcreto extends MetodoEnvio {
        @Override
        public void calcularCosto() {
            this.tarifaInterna = 50.0; // Ejemplo de tarifa interna
        }
    }

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private DetallePedidoRepository detallePedidoRepository;

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearPedido() {
        // Mock del cliente
        Cliente clienteMock = mock(Cliente.class);

        // Mock de productos en el carrito
        Producto productoMock = new ProductoConcreto("Producto Test", "Descripción del producto", 100.0);

        Map<Producto, Integer> productosCarrito = new HashMap<>();
        productosCarrito.put(productoMock, 2);

        // Mock de Método de Envío
        MetodoEnvio metodoEnvioMock = new MetodoEnvioConcreto();

        // Configurar ID de pago y estado
        String idPagoMP = "pago123";
        String status = "approved";

        // Mock de DetallePedido
        DetallePedido detalleMock = new DetallePedido(2, productoMock);
        when(detallePedidoRepository.save(any(DetallePedido.class))).thenReturn(detalleMock);

        // Mock de PagoMercadoPago
        PagoMercadoPago pagoMock = new PagoMercadoPago(idPagoMP, status);
        when(pagoRepository.save(any(PagoMercadoPago.class))).thenReturn(pagoMock);

        // Mock de Pedido
        Pedido pedidoMock = new Pedido(metodoEnvioMock, pagoMock, List.of(detalleMock), clienteMock);
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoMock);

        // Ejecutar el método
        Pedido pedido = pedidoService.crearPedido(clienteMock, productosCarrito, idPagoMP, status, metodoEnvioMock);

        // Verificar el resultado
        assertNotNull(pedido);
        assertEquals(clienteMock, pedido.getCliente());
        assertEquals(1, pedido.getDetallesPedido().size());
        assertEquals(pagoMock, pedido.getPago());
        assertEquals(metodoEnvioMock, pedido.getMetodoEnvio());

        // Verificar interacciones
        verify(detallePedidoRepository, times(1)).save(any(DetallePedido.class));
        verify(pagoRepository, times(1)).save(any(PagoMercadoPago.class));
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }
}
