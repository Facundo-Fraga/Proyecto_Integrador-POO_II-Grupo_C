package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.DetallePedido;
import edu.unam.ecomarket.modelo.Producto;

class DetallePedidoTest {

    private Producto productoMock;

    @BeforeEach
    void setUp() {
        productoMock = mock(Producto.class);
    }

    // Test para verificar el cálculo correcto del subtotal
    @Test
    void testCalcularSubTotal() {
        when(productoMock.getPrecioFinal()).thenReturn(50.0);

        DetallePedido detallePedido = new DetallePedido(2, productoMock);

        assertEquals(100.0, detallePedido.getSubTotal(), 0.01);
    }

    // Test para verificar la inicialización de los atributos
    @Test
    void testInicializacion() {
        when(productoMock.getPrecioFinal()).thenReturn(30.0);

        DetallePedido detallePedido = new DetallePedido(3, productoMock);

        assertEquals(3, detallePedido.getCantidad());
        assertEquals(productoMock, detallePedido.getProducto());
        assertEquals(90.0, detallePedido.getSubTotal(), 0.01);
    }

    // Test para asegurar que el subtotal se actualiza correctamente con cantidades diferentes
    @Test
    void testActualizarCantidad() {
        when(productoMock.getPrecioFinal()).thenReturn(20.0);

        DetallePedido detallePedido = new DetallePedido(1, productoMock);
        detallePedido.setCantidad(5);

        double nuevoSubTotal = productoMock.getPrecioFinal() * detallePedido.getCantidad();
        detallePedido.setSubTotal(nuevoSubTotal);

        assertEquals(5, detallePedido.getCantidad());
        assertEquals(100.0, detallePedido.getSubTotal(), 0.01);
    }
}
