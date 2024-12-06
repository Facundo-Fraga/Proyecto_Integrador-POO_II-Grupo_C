package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.Descuento;

class ProductoTest {

    // Implementación concreta mínima para la clase abstracta Producto
    static class ProductoConcreto extends Producto {
        public ProductoConcreto(String nombre, String descripcion, double precioBase) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precioBase = precioBase;
        }
    }

    private Producto producto;
    private Descuento descuentoMock;

    @BeforeEach
    void setUp() {
        producto = new ProductoConcreto("Producto de Prueba", "Descripción de prueba", 100.0);
        descuentoMock = mock(Descuento.class);
    }

    @Test
    void testInicializacion() {
        assertEquals("Producto de Prueba", producto.getNombre());
        assertEquals("Descripción de prueba", producto.getDescripcion());
        assertEquals(100.0, producto.getPrecioBase(), 0.01);
    }

    @Test
    void testTieneDescuento_SinDescuentos() {
        producto.setDescuentos(new ArrayList<>());
        assertFalse(producto.tieneDescuento());
    }

    @Test
    void testTieneDescuento_ConDescuentosNoAplicables() {
        when(descuentoMock.esAplicable()).thenReturn(false);
        List<Descuento> descuentos = new ArrayList<>();
        descuentos.add(descuentoMock);
        producto.setDescuentos(descuentos);

        assertFalse(producto.tieneDescuento());
    }

    @Test
    void testTieneDescuento_ConDescuentosAplicables() {
        when(descuentoMock.esAplicable()).thenReturn(true);
        List<Descuento> descuentos = new ArrayList<>();
        descuentos.add(descuentoMock);
        producto.setDescuentos(descuentos);

        assertTrue(producto.tieneDescuento());
    }

    @Test
    void testGetPrecioFinal_SinDescuentos() {
        producto.setDescuentos(new ArrayList<>());
        assertEquals(100.0, producto.getPrecioFinal(), 0.01);
    }

    @Test
    void testGetPrecioFinal_ConDescuentosAplicables() {
        when(descuentoMock.esAplicable()).thenReturn(true);
        when(descuentoMock.aplicarDescuento(100.0)).thenReturn(80.0);

        List<Descuento> descuentos = new ArrayList<>();
        descuentos.add(descuentoMock);
        producto.setDescuentos(descuentos);

        assertEquals(80.0, producto.getPrecioFinal(), 0.01);
    }
}
