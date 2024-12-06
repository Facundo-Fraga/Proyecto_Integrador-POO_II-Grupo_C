package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un Pedido en el sistema.
 * 
 * <p>
 * Un pedido incluye detalles sobre los productos comprados, el método de envío,
 * el cliente asociado, el pago realizado y el total del pedido.
 * </p>
 * 
 * <p>
 * Está mapeada como una entidad en la base de datos con relaciones hacia
 * {@link MetodoEnvio}, {@link Cliente}, {@link Pago} y {@link DetallePedido}.
 * </p>
 * 
 * <p>
 * El total del pedido incluye la suma de los subtotales de los detalles del pedido
 * más la tarifa del método de envío.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    /**
     * Identificador único del pedido.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_sequence", allocationSize = 1)
    private Long idPedido;

    /**
     * Fecha y hora en la que se creó el pedido.
     * Este valor se inicializa automáticamente al momento de crear la entidad.
     */
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime fecha = LocalDateTime.now();

    /**
     * Total del pedido, que incluye los subtotales de los detalles y la tarifa de envío.
     */
    @Column
    private double total;

    /**
     * Método de envío asociado al pedido.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "metodo_envio_id")
    private MetodoEnvio metodoEnvio;

    /**
     * Cliente asociado al pedido.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /**
     * Pago asociado al pedido.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Pago pago;

    /**
     * Lista de detalles del pedido, que contiene los productos y sus cantidades.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<DetallePedido> detallesPedido = new ArrayList<>();

    /**
     * Constructor que inicializa un pedido con los detalles proporcionados.
     * 
     * @param metodoEnvio    Método de envío del pedido.
     * @param pago           Pago realizado para el pedido.
     * @param detallesPedido Lista de detalles del pedido.
     * @param cliente        Cliente asociado al pedido.
     */
    public Pedido(MetodoEnvio metodoEnvio, Pago pago, List<DetallePedido> detallesPedido, Cliente cliente) {
        this.metodoEnvio = metodoEnvio;
        this.pago = pago;
        this.detallesPedido = detallesPedido;
        this.cliente = cliente;
        this.total = calcularTotal(detallesPedido);
    }

    /**
     * Calcula el total del pedido sumando los subtotales de los detalles más la tarifa de envío.
     * 
     * @param detallesPedido Lista de detalles del pedido.
     * @return Total calculado del pedido.
     */
    private double calcularTotal(List<DetallePedido> detallesPedido) {
        double total = metodoEnvio.getTarifaInterna();
        for (DetallePedido detallePedido : detallesPedido) {
            total += detallePedido.getSubTotal();
        }
        return total;
    }

    /**
     * Agrega un detalle al pedido y actualiza el total.
     * 
     * @param detallePedido Detalle del pedido a agregar.
     */
    public void agregarDetalle(DetallePedido detallePedido) {
        this.detallesPedido.add(detallePedido);
        this.setTotal(calcularTotal(this.detallesPedido));
    }
}
