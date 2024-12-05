package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_sequence", allocationSize = 1)
    private Long idPedido;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime fecha = LocalDateTime.now();

    @Column
    private double total;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "metodo_envio_id")
    private MetodoEnvio metodoEnvio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")  // La columna que se usará para la relación
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL)
    private Pago pago;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<DetallePedido> detallesPedido = new ArrayList<>();

    public Pedido(MetodoEnvio metodoEnvio, Pago pago, List<DetallePedido> detallesPedido,  Cliente cliente) {
        this.metodoEnvio = metodoEnvio;
        this.pago = pago;
        this.detallesPedido = detallesPedido;
        this.cliente = cliente;
        this.total = calcularTotal(detallesPedido);
    }

    private double calcularTotal(List<DetallePedido> detallesPedido) {
        double total = metodoEnvio.getTarifaInterna();
        for (DetallePedido detallePedido : detallesPedido) {
            total += detallePedido.getSubTotal();
        }
        return total;
    }

    public void agregarDetalle(DetallePedido detallePedido) {
        this.detallesPedido.add(detallePedido);
        this.setTotal(calcularTotal(this.detallesPedido));
    }
}
