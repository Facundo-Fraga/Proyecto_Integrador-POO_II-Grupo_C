package edu.unam.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Clase abstracta que representa un método de envío.
 * 
 * <p>
 * Esta clase sirve como base para diferentes tipos de envíos (local, provincial, nacional),
 * implementados mediante herencia. Utiliza una tabla única para almacenar todas las
 * estrategias, diferenciadas por la columna de discriminación {@code metodo_envio}.
 * </p>
 * 
 * <p>
 * Cada método de envío tiene un número de seguimiento único y una tarifa interna que
 * debe calcularse en las subclases.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Table(name = "envio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "metodo_envio")
@Getter
@NoArgsConstructor
public abstract class MetodoEnvio {

    /**
     * Número de seguimiento único para el envío.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "envio_seq")
    @SequenceGenerator(name = "envio_seq", sequenceName = "envio_sequence", allocationSize = 1)
    protected Long nroSeguimiento;

    /**
     * Tarifa interna del envío.
     * 
     * <p>
     * Este valor debe ser calculado en las subclases mediante el método {@link #calcularCosto()}.
     * </p>
     */
    @Column(nullable = false)
    protected double tarifaInterna = 0;

    /**
     * Método abstracto para calcular el costo del envío.
     * 
     * <p>
     * Las subclases deben implementar este método para definir el costo correspondiente
     * según el tipo de envío.
     * </p>
     */
    public abstract void calcularCosto();
}
