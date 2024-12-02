package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.unam.ecomarket.modelo.payment.PagoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    // a un pedido le corresponde un metodo de Envio
    @Column
    private double total;

    @OneToOne
    private MetodoEnvio metodoEnvio;

    @OneToOne(cascade = CascadeType.ALL)
    private Pago pago;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<DetallePedido> detallesPedido = new ArrayList<>();

    public Pedido(MetodoEnvio metodoEnvio, Pago pago, ArrayList<DetallePedido> detallesPedido) {
        this.metodoEnvio = metodoEnvio;
        this.pago = pago;
        this.detallesPedido = detallesPedido;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double total = metodoEnvio.getTarifaInterna();
        for (DetallePedido detallePedido : detallesPedido) {
            total += detallePedido.getSubTotal();
        }
        return total;
    }

    public void agregarDetalle(DetallePedido detallePedido) {
        this.detallesPedido.add(detallePedido);
        this.setTotal(calcularTotal());
    }

}
