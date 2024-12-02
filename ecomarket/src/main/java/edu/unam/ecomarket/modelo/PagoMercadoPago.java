package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PagoMercadoPago extends Pago {
    @Column(name = "monto", precision = 19, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private String moneda = "ARS";

    public PagoMercadoPago(BigDecimal monto) {
        this.monto = monto;
    }
}
