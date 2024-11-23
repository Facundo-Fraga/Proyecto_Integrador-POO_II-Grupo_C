package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private LocalDateTime fecha;
    
    // a un pedido le corresponde un metodo de pago
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)


    

    private Cliente cliente;
    // a un pedido le corresponde un metodo de pago

    // a un pedido le corresponde un metodo de Envio
    @OneToOne
    private MetodoEnvio metodoEnvio;
    private ArrayList<DetallePedido> listaDetallesPedido;

}
