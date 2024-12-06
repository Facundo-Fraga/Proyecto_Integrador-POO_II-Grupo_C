package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.PagoMercadoPago;

class PagoMercadoPagoTest {

    @Test
    void testInicializacionConConstructor() throws NoSuchFieldException, IllegalAccessException {
        String idPagoMP = "12345";
        String estado = "APROBADO";

        PagoMercadoPago pago = new PagoMercadoPago(idPagoMP, estado);

        Field monedaField = PagoMercadoPago.class.getDeclaredField("moneda");
        Field idPagoMPField = PagoMercadoPago.class.getDeclaredField("idPagoMP");
        Field estadoField = PagoMercadoPago.class.getDeclaredField("estado");

        monedaField.setAccessible(true);
        idPagoMPField.setAccessible(true);
        estadoField.setAccessible(true);

        assertEquals("ARS", monedaField.get(pago));
        assertEquals(idPagoMP, idPagoMPField.get(pago));
        assertEquals(estado, estadoField.get(pago));
    }

    @Test
    void testValoresPorDefecto() throws NoSuchFieldException, IllegalAccessException {
        PagoMercadoPago pago = new PagoMercadoPago();

        Field monedaField = PagoMercadoPago.class.getDeclaredField("moneda");
        Field idPagoMPField = PagoMercadoPago.class.getDeclaredField("idPagoMP");
        Field estadoField = PagoMercadoPago.class.getDeclaredField("estado");

        monedaField.setAccessible(true);
        idPagoMPField.setAccessible(true);
        estadoField.setAccessible(true);

        assertEquals("ARS", monedaField.get(pago));
        assertNull(idPagoMPField.get(pago));
        assertNull(estadoField.get(pago));
    }

    @Test
    void testSetAtributos() throws NoSuchFieldException, IllegalAccessException {
        PagoMercadoPago pago = new PagoMercadoPago();

        Field idPagoMPField = PagoMercadoPago.class.getDeclaredField("idPagoMP");
        Field estadoField = PagoMercadoPago.class.getDeclaredField("estado");

        idPagoMPField.setAccessible(true);
        estadoField.setAccessible(true);

        String nuevoIdPagoMP = "67890";
        String nuevoEstado = "PENDIENTE";

        idPagoMPField.set(pago, nuevoIdPagoMP);
        estadoField.set(pago, nuevoEstado);

        assertEquals(nuevoIdPagoMP, idPagoMPField.get(pago));
        assertEquals(nuevoEstado, estadoField.get(pago));
    }
}
