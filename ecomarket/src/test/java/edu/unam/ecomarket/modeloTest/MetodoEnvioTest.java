package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.MetodoEnvio;

class MetodoEnvioTest {

    // Test para verificar los valores iniciales de los atributos
    @Test
    void testValoresIniciales() {
        MetodoEnvio envio = new MetodoEnvio() {
            @Override
            public void calcularCosto() {
                tarifaInterna = 1000; // Implementación de ejemplo
            }
        };

        assertEquals(0, envio.getTarifaInterna(), 0.01);
        assertNull(envio.getNroSeguimiento());
    }

    // Test para verificar que una subclase concreta implemente calcularCosto
    @Test
    void testCalcularCostoImplementado() {
        MetodoEnvio envio = new MetodoEnvio() {
            @Override
            public void calcularCosto() {
                tarifaInterna = 1500; // Implementación concreta
            }
        };

        envio.calcularCosto();

        assertEquals(1500, envio.getTarifaInterna(), 0.01);
    }
}
