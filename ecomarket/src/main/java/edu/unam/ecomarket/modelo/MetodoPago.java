package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;

public interface MetodoPago {

 boolean pagar(BigDecimal monto);
 void obtenerDetalles();  
}
