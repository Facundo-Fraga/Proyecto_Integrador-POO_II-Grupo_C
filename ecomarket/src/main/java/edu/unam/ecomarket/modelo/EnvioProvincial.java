package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase que representa un método de envío provincial.
 * 
 * <p>
 * Hereda de la clase {@link MetodoEnvio} y define el costo específico para envíos provinciales.
 * El costo es fijo y se asigna al atributo {@code tarifaInterna}.
 * </p>
 * 
 * <p>
 * Esta clase utiliza el patrón de herencia en JPA, diferenciándose por el valor
 * "provincial" en la columna de discriminación.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@DiscriminatorValue("provincial")
public class EnvioProvincial extends MetodoEnvio {

    /**
     * Calcula el costo del envío provincial y lo asigna a la tarifa interna.
     * 
     * <p>
     * Para envíos provinciales, la tarifa es fija y tiene un valor de 14000.
     * </p>
     */
    @Override
    public void calcularCosto() {
        this.tarifaInterna = 14000;
    }
}
