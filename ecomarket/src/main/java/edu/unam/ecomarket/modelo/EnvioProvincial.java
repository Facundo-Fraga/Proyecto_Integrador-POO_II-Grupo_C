package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("provincial")
public class EnvioProvincial extends MetodoEnvio {
    @Override
    public void calcularCosto() {
        this.tarifaInterna = 14000;    
    }
}
