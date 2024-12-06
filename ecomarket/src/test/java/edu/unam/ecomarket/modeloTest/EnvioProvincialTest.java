package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.EnvioProvincial;

class EnvioProvincialTest {

    private EnvioProvincial envioProvincial;

    @BeforeEach
    void setUp() {
        envioProvincial = new EnvioProvincial();
    }

    // Test para verificar el cálculo de costo para envío provincial
    @Test
    void testCalcularCosto() {
        envioProvincial.calcularCosto();

        assertEquals(14000, envioProvincial.getTarifaInterna(), 0.01);
    }

    // Test para verificar la tarifa inicial antes de calcular
    @Test
    void testTarifaInicial() {
        assertEquals(0, envioProvincial.getTarifaInterna(), 0.01);
    }
}
