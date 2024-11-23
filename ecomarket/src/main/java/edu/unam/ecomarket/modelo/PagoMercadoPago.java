package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;

import org.w3c.dom.events.MouseEvent;

public class PagoMercadoPago implements MetodoPago {
   
    private String emailUsuario;
    private String tokenAcceso;
    @Override
    public boolean pagar(BigDecimal monto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagar'");
    }
    @Override
    public void obtenerDetalles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerDetalles'");
    }

   

}
