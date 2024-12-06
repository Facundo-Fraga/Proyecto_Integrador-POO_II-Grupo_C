package edu.unam.ecomarket.modeloTest;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.Pago;

import static org.junit.jupiter.api.Assertions.*;

class PagoTest {

    @Test
    void testValoresIniciales() throws NoSuchFieldException, IllegalAccessException {
        Pago pago = new Pago() {
            // Implementación concreta mínima para la clase abstracta
        };

        Field idPagoField = Pago.class.getDeclaredField("idPago");
        Field fechaPagoField = Pago.class.getDeclaredField("fechaPago");

        idPagoField.setAccessible(true);
        fechaPagoField.setAccessible(true);

        assertNull(idPagoField.get(pago));
        assertNotNull(fechaPagoField.get(pago));
        assertTrue(((LocalDateTime) fechaPagoField.get(pago)).isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testAsignacionAtributos() throws NoSuchFieldException, IllegalAccessException {
        Pago pago = new Pago() {
            // Implementación concreta mínima para la clase abstracta
        };

        Field fechaPagoField = Pago.class.getDeclaredField("fechaPago");
        fechaPagoField.setAccessible(true);

        LocalDateTime fechaActual = LocalDateTime.now();
        fechaPagoField.set(pago, fechaActual);

        assertEquals(fechaActual, fechaPagoField.get(pago));
    }
}
