package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una estrategia de descuento basada en un porcentaje.
 * 
 * <p>
 * Hereda de la clase {@link EstrategiaDescuento} y define cómo se aplica el descuento
 * calculando un porcentaje del precio original y restándolo. 
 * </p>
 * 
 * <p>
 * Esta clase utiliza el patrón de herencia en JPA, diferenciándose por el valor
 * "PORCENTAJE" en la columna de discriminación.
 * </p>
 * 
 * <p>
 * Ejemplo de cálculo: Si el precio es 100 y el porcentaje de descuento es 20, el precio
 * final será 80.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@DiscriminatorValue("PORCENTAJE")
@NoArgsConstructor
public class DescuentoPorcentaje extends EstrategiaDescuento {

    /**
     * Aplica un descuento basado en un porcentaje al precio proporcionado.
     * 
     * @param precio Precio original al que se aplicará el descuento.
     * @return Precio final después de aplicar el descuento.
     */
    @Override
    public Double aplicarDescuento(Double precio) {
        return precio - (precio * (valorDescuento / 100));
    }
}
