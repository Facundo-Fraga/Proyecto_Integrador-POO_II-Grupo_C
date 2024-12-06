package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;

class ProductoSingularTest {

    private ProductoSingular productoSingular;
    private ProductoPaquete paqueteMock;

    @BeforeEach
    void setUp() {
        productoSingular = new ProductoSingular();
        productoSingular.setNombre("Producto Singular");
        productoSingular.setPrecioBase(100.0);

        paqueteMock = mock(ProductoPaquete.class);
    }

    @Test
    void testAgregarPaqueteContenedor() {
        productoSingular.agregarPaqueteContenedor(paqueteMock);

        assertEquals(1, productoSingular.getPaquetesContenedores().size());
        assertTrue(productoSingular.getPaquetesContenedores().contains(paqueteMock));
        verify(paqueteMock, times(1)).agregarProducto(productoSingular);
    }

    @Test
    void testEliminarPaqueteContenedor() {
        productoSingular.agregarPaqueteContenedor(paqueteMock);
        productoSingular.eliminarPaqueteContenedor(paqueteMock);

        assertEquals(0, productoSingular.getPaquetesContenedores().size());
        assertFalse(productoSingular.getPaquetesContenedores().contains(paqueteMock));
        verify(paqueteMock, times(1)).eliminarProducto(productoSingular);
    }

    @Test
    void testAgregarDetalle() {
        Map<String, String> detalles = new HashMap<>();
        detalles.put("Color", "Rojo");
        detalles.put("Tamaño", "Mediano");

        productoSingular.setDetalles(detalles);

        assertEquals(2, productoSingular.getDetalles().size());
        assertEquals("Rojo", productoSingular.getDetalles().get("Color"));
        assertEquals("Mediano", productoSingular.getDetalles().get("Tamaño"));
    }

    @Test
    void testActualizarDetalle() {
        productoSingular.getDetalles().put("Color", "Azul");

        productoSingular.getDetalles().put("Color", "Verde");

        assertEquals(1, productoSingular.getDetalles().size());
        assertEquals("Verde", productoSingular.getDetalles().get("Color"));
    }

    @Test
    void testEliminarDetalle() {
        productoSingular.getDetalles().put("Material", "Plástico");
        productoSingular.getDetalles().remove("Material");

        assertTrue(productoSingular.getDetalles().isEmpty());
    }
}
