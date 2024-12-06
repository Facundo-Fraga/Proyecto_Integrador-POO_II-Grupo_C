package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.Descuento;
import edu.unam.ecomarket.modelo.DescuentoFijo;
import edu.unam.ecomarket.modelo.DescuentoPorcentaje;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;
import edu.unam.ecomarket.modelo.Producto;

class DescuentoTest {

    private Descuento descuento;

    @BeforeEach
    void setUp() {
        descuento = new Descuento();
    }

    // Test para verificar si el descuento es aplicable en la fecha actual
    @Test
    void testEsAplicable() {
        descuento.setFechaInicio(LocalDate.now().minusDays(1));
        descuento.setFechaFin(LocalDate.now().plusDays(1));

        assertTrue(descuento.esAplicable());

        descuento.setFechaInicio(LocalDate.now().plusDays(1));
        descuento.setFechaFin(LocalDate.now().plusDays(2));

        assertFalse(descuento.esAplicable());
    }

    // Test para aplicar un descuento con estrategia de porcentaje
    @Test
    void testAplicarDescuentoPorcentaje() {
        // Crear una subclase concreta de EstrategiaDescuento
        EstrategiaDescuento estrategia = new EstrategiaDescuento() {
            @Override
            public Double aplicarDescuento(Double precio) {
                return precio * 0.8; // Aplicar un 20% de descuento
            }

            @Override
            public Double getValorDescuento() {
                return 20.0; // Representa el porcentaje
            }
        };

        descuento.setEstrategia(estrategia);

        // Probar el método aplicarDescuento
        Double precioFinal = descuento.aplicarDescuento(100.0);

        assertEquals(80.0, precioFinal);
    }

    // Test para aplicar un descuento con estrategia fija
    @Test
    void testAplicarDescuentoFijo() {
        // Crear una subclase concreta de EstrategiaDescuento
        EstrategiaDescuento estrategia = new EstrategiaDescuento() {
            @Override
            public Double aplicarDescuento(Double precio) {
                return precio - 10.0; // Aplicar un descuento fijo de 10
            }

            @Override
            public Double getValorDescuento() {
                return 10.0; // Representa el descuento fijo
            }
        };

        descuento.setEstrategia(estrategia);

        // Probar el método aplicarDescuento
        Double precioFinal = descuento.aplicarDescuento(100.0);

        assertEquals(90.0, precioFinal);
    }

    // Test para verificar el manejo de productos asociados al descuento
    @Test
    void testSetProductos() {
        Producto productoMock1 = mock(Producto.class);
        Producto productoMock2 = mock(Producto.class);
        List<Producto> productos = List.of(productoMock1, productoMock2);

        boolean resultado = descuento.setProductos(productos);

        assertTrue(resultado);
        verify(productoMock1, times(1)).getDescuentos();
        verify(productoMock2, times(1)).getDescuentos();
    }

    // Test para obtener el valor del descuento desde la estrategia
    @Test
    void testGetValorDescuento() {
        EstrategiaDescuento estrategiaMock = mock(EstrategiaDescuento.class);
        when(estrategiaMock.getValorDescuento()).thenReturn(20.0);

        descuento.setEstrategia(estrategiaMock);

        Double valorDescuento = descuento.getValorDescuento();

        assertEquals(20.0, valorDescuento);
        verify(estrategiaMock, times(1)).getValorDescuento();
    }
}
