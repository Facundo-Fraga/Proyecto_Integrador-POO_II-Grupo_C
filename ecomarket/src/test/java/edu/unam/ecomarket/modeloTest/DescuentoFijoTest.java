package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.DescuentoFijo;

class DescuentoFijoTest {

    private DescuentoFijo descuentoFijo;

    @BeforeEach
    void setUp() {
        descuentoFijo = new DescuentoFijo();
    }

    // Test para aplicar un descuento fijo normal
    @Test
    void testAplicarDescuento() {
        descuentoFijo.setValorDescuento(20.0);

        Double precioFinal = descuentoFijo.aplicarDescuento(100.0);

        assertEquals(80.0, precioFinal, 0.01);
    }

    // Test para garantizar que el precio no caiga por debajo del mínimo permitido
    @Test
    void testAplicarDescuentoPrecioMinimo() {
        descuentoFijo.setValorDescuento(50.0);

        Double precioFinal = descuentoFijo.aplicarDescuento(1.0);

        assertEquals(1.0, precioFinal, 0.01); // Precio mínimo
    }
}
