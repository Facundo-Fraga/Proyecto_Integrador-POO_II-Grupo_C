package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.EnvioLocal;

class EnvioLocalTest {

    private EnvioLocal envioLocal;

    @BeforeEach
    void setUp() {
        envioLocal = new EnvioLocal();
    }

    // Test para verificar el cálculo de costo para envío local
    @Test
    void testCalcularCosto() {
        envioLocal.calcularCosto();

        assertEquals(5000, envioLocal.getTarifaInterna(), 0.01);
    }

    // Test para verificar la tarifa inicial antes de calcular
    @Test
    void testTarifaInicial() {
        assertEquals(0, envioLocal.getTarifaInterna(), 0.01);
    }
}
