package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.DescuentoPorcentaje;

class DescuentoPorcentajeTest {

    private DescuentoPorcentaje descuentoPorcentaje;

    @BeforeEach
    void setUp() {
        descuentoPorcentaje = new DescuentoPorcentaje();
    }

    // Test para aplicar un descuento por porcentaje
    @Test
    void testAplicarDescuento() {
        descuentoPorcentaje.setValorDescuento(20.0); // 20% de descuento

        Double precioFinal = descuentoPorcentaje.aplicarDescuento(100.0);

        assertEquals(80.0, precioFinal, 0.01);
    }

    // Test para verificar un descuento del 0% (sin cambios)
    @Test
    void testAplicarDescuentoCeroPorciento() {
        descuentoPorcentaje.setValorDescuento(0.0); // 0% de descuento

        Double precioFinal = descuentoPorcentaje.aplicarDescuento(100.0);

        assertEquals(100.0, precioFinal, 0.01); // Precio sin cambios
    }

    // Test para verificar un descuento del 100% (gratis)
    @Test
    void testAplicarDescuentoCienPorciento() {
        descuentoPorcentaje.setValorDescuento(100.0); // 100% de descuento

        Double precioFinal = descuentoPorcentaje.aplicarDescuento(100.0);

        assertEquals(0.0, precioFinal, 0.01); // Precio gratis
    }
}
