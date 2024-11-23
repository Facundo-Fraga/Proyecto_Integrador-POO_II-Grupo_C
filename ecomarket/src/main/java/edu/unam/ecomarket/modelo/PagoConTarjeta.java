package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PagoConTarjeta implements MetodoPago {
   
    private String numeroTarjeta;
    private String nombreTitular;
    private Date fechaExpiracion;
    private String cvv;
   
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
