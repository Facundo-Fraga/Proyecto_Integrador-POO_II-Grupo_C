package edu.unam.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase abstracta que representa una estrategia de descuento.
 * 
 * <p>
 * Esta clase es la base para implementar diferentes estrategias de descuento,
 * como descuentos basados en un porcentaje o un monto fijo. Utiliza la herencia
 * en JPA con una tabla única para almacenar todas las estrategias, diferenciadas
 * por la columna de discriminación {@code tipo_estrategia}.
 * </p>
 * 
 * <p>
 * Cada estrategia tiene un identificador único y un valor de descuento específico.
 * Las subclases deben implementar el método {@code aplicarDescuento} para definir
 * cómo se aplica la estrategia al precio.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_estrategia")
@Getter
@Setter
@NoArgsConstructor
public abstract class EstrategiaDescuento {

    /**
     * Identificador único de la estrategia de descuento.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estrategia_seq")
    @SequenceGenerator(name = "estrategia_seq", sequenceName = "estrategia_sequence", allocationSize = 1)
    private Long id;

    /**
     * Valor del descuento definido por la estrategia.
     * Puede representar un porcentaje o un monto fijo, dependiendo de la implementación.
     */
    protected Double valorDescuento;

    /**
     * Método abstracto que aplica el descuento al precio proporcionado.
     * 
     * <p>
     * Las subclases deben implementar este método para definir cómo se calcula
     * el descuento en función de la estrategia.
     * </p>
     * 
     * @param precio Precio original al que se aplicará el descuento.
     * @return Precio final después de aplicar el descuento.
     */
    public abstract Double aplicarDescuento(Double precio);
}
