package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("MONTO_FIJO")
@NoArgsConstructor
public class DescuentoFijo extends EstrategiaDescuento {

    public Double aplicarDescuento(Double precio) {
        return Math.max(1, precio - valorDescuento);
    }
}
