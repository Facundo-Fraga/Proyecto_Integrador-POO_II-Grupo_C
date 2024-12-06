package edu.unam.ecomarket.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un pago realizado a través de MercadoPago.
 * 
 * <p>
 * Hereda de la clase abstracta {@link Pago} y añade información específica de los pagos realizados mediante la plataforma MercadoPago,
 * como el identificador del pago, el estado del pago y la moneda utilizada.
 * </p>
 * 
 * <p>
 * La moneda predeterminada es el peso argentino (ARS).
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@NoArgsConstructor
public class PagoMercadoPago extends Pago {

    /**
     * Moneda utilizada para el pago.
     * 
     * <p>
     * El valor predeterminado es "ARS" (pesos argentinos).
     * </p>
     */
    @Column(nullable = false)
    private String moneda = "ARS";

    /**
     * Identificador único del pago en MercadoPago.
     */
    @Column
    private String idPagoMP;

    /**
     * Estado del pago en MercadoPago.
     * 
     * <p>
     * Puede ser, por ejemplo, "aprobado", "pendiente" o "rechazado".
     * </p>
     */
    @Column
    private String estado;

    /**
     * Constructor que inicializa un pago de MercadoPago con su identificador y estado.
     * 
     * @param idPagoMP Identificador único del pago en MercadoPago.
     * @param estado   Estado del pago.
     */
    public PagoMercadoPago(String idPagoMP, String estado) {
        this.idPagoMP = idPagoMP;
        this.estado = estado;
    }
}
