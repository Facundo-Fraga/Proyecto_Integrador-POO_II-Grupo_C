package edu.unam.ecomarket.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un detalle de un pedido en el sistema.
 * 
 * <p>
 * Un detalle de pedido incluye información sobre un producto específico,
 * la cantidad comprada y el subtotal calculado en función del precio del producto.
 * </p>
 * 
 * <p>
 * Esta clase está mapeada como una entidad en la base de datos y está relacionada
 * con la entidad {@link Producto}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Table(name = "detallePedido")
@Getter
@Setter
@NoArgsConstructor
public class DetallePedido {

    /**
     * Identificador único del detalle del pedido.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_pedido_seq")
    @SequenceGenerator(name = "detalle_pedido_seq", sequenceName = "detalle_pedido_sequence", allocationSize = 1)
    private Long idDetallePedido;

    /**
     * Cantidad de unidades del producto en este detalle.
     */
    @Column
    private int cantidad;

    /**
     * Subtotal calculado para este detalle, basado en la cantidad y el precio del producto.
     */
    @Column
    private double subTotal;

    /**
     * Producto asociado a este detalle del pedido.
     */
    @ManyToOne
    @JoinColumn(name = "producto_id_producto")
    private Producto producto;

    /**
     * Constructor para inicializar un detalle de pedido con cantidad y producto.
     * 
     * <p>
     * Calcula automáticamente el subtotal basándose en el precio del producto y la cantidad.
     * </p>
     * 
     * @param cantidad Cantidad de unidades del producto.
     * @param producto Producto asociado al detalle.
     */
    public DetallePedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.subTotal = calcularSubTotal(producto, cantidad);
    }

    /**
     * Calcula el subtotal para este detalle de pedido.
     * 
     * <p>
     * Multiplica el precio final del producto por la cantidad especificada.
     * </p>
     * 
     * @param producto Producto asociado al detalle.
     * @param cantidad Cantidad de unidades del producto.
     * @return El subtotal calculado.
     */
    private double calcularSubTotal(Producto producto, int cantidad) {
        return producto.getPrecioFinal() * cantidad;
    }
}
