package edu.unam.ecomarket.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PagoMercadoPago extends Pago {
    
    @Column(nullable = false)
    private String moneda = "ARS";
    
    @Column
    private String idPagoMP;
    
    @Column 
    private String estado;

    public PagoMercadoPago(String idPagoMP, String estado) {
        this.idPagoMP = idPagoMP;
        this.estado = estado;
    }
}

