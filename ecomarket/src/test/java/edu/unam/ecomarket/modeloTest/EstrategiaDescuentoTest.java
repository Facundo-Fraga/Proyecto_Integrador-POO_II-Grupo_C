package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.EstrategiaDescuento;

class EstrategiaDescuentoTest {

    // Test para verificar los getters y setters del valor de descuento
    @Test
    void testSetAndGetValorDescuento() {
        EstrategiaDescuento estrategiaMock = mock(EstrategiaDescuento.class);

        estrategiaMock.setValorDescuento(20.0);
        when(estrategiaMock.getValorDescuento()).thenReturn(20.0);

        assertEquals(20.0, estrategiaMock.getValorDescuento(), 0.01);
        verify(estrategiaMock, times(1)).setValorDescuento(20.0);
        verify(estrategiaMock, times(1)).getValorDescuento();
    }

    // Test para verificar que una subclase concreta implemente aplicarDescuento
    @Test
    void testAplicarDescuentoImplementado() {
        EstrategiaDescuento estrategia = new EstrategiaDescuento() {
            @Override
            public Double aplicarDescuento(Double precio) {
                return precio - 10.0;
            }
        };

        Double resultado = estrategia.aplicarDescuento(100.0);

        assertEquals(90.0, resultado, 0.01);
    }

    // Test para verificar el comportamiento de la clase con valor de descuento nulo
    @Test
    void testValorDescuentoNulo() {
        EstrategiaDescuento estrategia = new EstrategiaDescuento() {
            @Override
            public Double aplicarDescuento(Double precio) {
                return precio; // Sin descuento
            }
        };

        estrategia.setValorDescuento(null);

        assertNull(estrategia.getValorDescuento());
    }
}
