package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import jakarta.persistence.*;

/**
 * Clase abstracta que representa un pago en el sistema.
 * 
 * <p>
 * Esta clase sirve como base para diferentes tipos de pagos (por ejemplo, pago con tarjeta,
 * pago en efectivo). Utiliza una tabla única para almacenar todos los tipos de pagos,
 * diferenciados por la columna de discriminación {@code tipo_pago}.
 * </p>
 * 
 * <p>
 * Cada pago tiene un identificador único y una fecha de realización, que se establece
 * automáticamente al momento de crear la entidad.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pago")
public abstract class Pago {

    /**
     * Identificador único del pago.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_seq")
    @SequenceGenerator(name = "pago_seq", sequenceName = "pago_sequence", allocationSize = 1)
    @Column(name = "id_pago")
    private Long idPago;

    /**
     * Fecha y hora en que se realizó el pago.
     * 
     * <p>
     * Este valor se inicializa automáticamente al momento de crear la entidad.
     * </p>
     */
    @Column
    private LocalDateTime fechaPago = LocalDateTime.now();
}
