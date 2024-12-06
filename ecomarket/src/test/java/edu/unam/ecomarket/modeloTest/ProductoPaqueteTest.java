package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;

class ProductoPaqueteTest {

    private ProductoPaquete paquete;
    private ProductoSingular productoMock1;
    private ProductoSingular productoMock2;

    @BeforeEach
    void setUp() {
        paquete = new ProductoPaquete();

        productoMock1 = mock(ProductoSingular.class);
        productoMock2 = mock(ProductoSingular.class);

        when(productoMock1.getPrecioBase()).thenReturn(100.0);
        when(productoMock2.getPrecioBase()).thenReturn(200.0);
    }

    @Test
    void testAgregarProducto() {
        paquete.agregarProducto(productoMock1);

        assertEquals(1, paquete.getProductos().size());
        assertTrue(paquete.getProductos().contains(productoMock1));
        assertEquals(90.0, paquete.getPrecioBase(), 0.01);
    }

    @Test
    void testAgregarProductoDuplicado() {
        paquete.agregarProducto(productoMock1);
        paquete.agregarProducto(productoMock1);

        assertEquals(1, paquete.getProductos().size());
        assertEquals(90.0, paquete.getPrecioBase(), 0.01);
    }

    @Test
    void testEliminarProducto() {
        paquete.agregarProducto(productoMock1);
        paquete.agregarProducto(productoMock2);

        paquete.eliminarProducto(productoMock1);

        assertEquals(1, paquete.getProductos().size());
        assertFalse(paquete.getProductos().contains(productoMock1));
        assertEquals(180.0, paquete.getPrecioBase(), 0.01);
    }

    @Test
    void testRecalcularPrecioBase() {
        paquete.agregarProducto(productoMock1);
        paquete.agregarProducto(productoMock2);

        double sumaPrecios = (100.0 + 200.0) * 0.9;

        assertEquals(sumaPrecios, paquete.getPrecioBase(), 0.01);
    }

    @Test
    void testEliminarProductoInexistente() {
        paquete.agregarProducto(productoMock1);

        paquete.eliminarProducto(productoMock2);

        assertEquals(1, paquete.getProductos().size());
        assertTrue(paquete.getProductos().contains(productoMock1));
        assertEquals(90.0, paquete.getPrecioBase(), 0.01);
    }
}
