package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("local")
public class EnvioLocal extends MetodoEnvio {
    @Override
    public void calcularCosto() {
        this.tarifaInterna = 7000;    
    }
}