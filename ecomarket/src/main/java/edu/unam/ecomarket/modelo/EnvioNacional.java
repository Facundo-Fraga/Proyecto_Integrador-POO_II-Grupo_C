package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase que representa un método de envío nacional.
 * 
 * <p>
 * Hereda de la clase {@link MetodoEnvio} y define el costo específico para envíos nacionales.
 * El costo es fijo y se asigna al atributo {@code tarifaInterna}.
 * </p>
 * 
 * <p>
 * Esta clase utiliza el patrón de herencia en JPA, diferenciándose por el valor
 * "nacional" en la columna de discriminación.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@DiscriminatorValue("nacional")
public class EnvioNacional extends MetodoEnvio {

    /**
     * Calcula el costo del envío nacional y lo asigna a la tarifa interna.
     * 
     * <p>
     * Para envíos nacionales, la tarifa es fija y tiene un valor de 20000.
     * </p>
     */
    @Override
    public void calcularCosto() {
        this.tarifaInterna = 20000;
    }
}
