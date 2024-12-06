package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.EnvioNacional;

class EnvioNacionalTest {

    private EnvioNacional envioNacional;

    @BeforeEach
    void setUp() {
        envioNacional = new EnvioNacional();
    }

    // Test para verificar el cálculo de costo para envío nacional
    @Test
    void testCalcularCosto() {
        envioNacional.calcularCosto();

        assertEquals(20000, envioNacional.getTarifaInterna(), 0.01);
    }

    // Test para verificar la tarifa inicial antes de calcular
    @Test
    void testTarifaInicial() {
        assertEquals(0, envioNacional.getTarifaInterna(), 0.01);
    }
}
