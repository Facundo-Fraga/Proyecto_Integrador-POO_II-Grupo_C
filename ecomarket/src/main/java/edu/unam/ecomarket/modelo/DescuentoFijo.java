package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una estrategia de descuento basada en un monto fijo.
 * 
 * <p>
 * Hereda de la clase {@link EstrategiaDescuento} y define cómo se aplica el descuento
 * restando un valor fijo al precio original. El descuento nunca reducirá el precio por
 * debajo de 1.
 * </p>
 * 
 * <p>
 * Esta clase utiliza el patrón de herencia en JPA, diferenciándose por el valor
 * "MONTO_FIJO" en la columna de discriminación.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@DiscriminatorValue("MONTO_FIJO")
@NoArgsConstructor
public class DescuentoFijo extends EstrategiaDescuento {

    /**
     * Aplica un descuento de monto fijo al precio proporcionado.
     * 
     * <p>
     * Si el precio resultante después de aplicar el descuento es menor que 1,
     * el precio final será ajustado automáticamente a 1.
     * </p>
     * 
     * @param precio Precio original al que se aplicará el descuento.
     * @return Precio final después de aplicar el descuento, con un mínimo de 1.
     */
    @Override
    public Double aplicarDescuento(Double precio) {
        return Math.max(1, precio - valorDescuento);
    }
}
