package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.*;

class PedidoTest {

    private MetodoEnvio envioMock;
    private Pago pagoMock;
    private Cliente clienteMock;
    private List<DetallePedido> detallesMock;

    @BeforeEach
    void setUp() {
        envioMock = new MetodoEnvio() {
            @Override
            public void calcularCosto() {
                this.tarifaInterna = 5000;
            }
        };

        pagoMock = new PagoMercadoPago("12345", "APROBADO");
        clienteMock = new Cliente();
        clienteMock.setNombre("Juan");
        clienteMock.setContrasenia("password123");
        clienteMock.setEmail("juan@example.com");

        detallesMock = new ArrayList<>();
        Producto productoMock = new ProductoSingular();
        productoMock.setNombre("Producto 1");
        productoMock.setPrecioBase(100.0);
        detallesMock.add(new DetallePedido(2, productoMock));
    }

    // Test para verificar la inicialización de atributos
    @Test
    void testInicializacion() {
        Pedido pedido = new Pedido(envioMock, pagoMock, detallesMock, clienteMock);

        assertEquals(envioMock, pedido.getMetodoEnvio());
        assertEquals(pagoMock, pedido.getPago());
        assertEquals(detallesMock, pedido.getDetallesPedido());
        assertEquals(clienteMock, pedido.getCliente());
    }
}