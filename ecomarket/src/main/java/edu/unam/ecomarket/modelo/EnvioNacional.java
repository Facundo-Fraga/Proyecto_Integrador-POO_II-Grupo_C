package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("nacional")
public class EnvioNacional extends MetodoEnvio {
    @Override
    public void calcularCosto() {
        this.tarifaInterna = 20000;    
    }
}