package edu.unam.ecomarket.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detallePedido")

@Getter
@Setter
@NoArgsConstructor


public class DetallePedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_sequence", allocationSize = 1)

    private Long idDetallePedido;
    
    @Column

    private Long cantidad;
    
    @Column

    private double precioTotal;
    
    @ManyToOne
    @JoinColumn(name = "pedido_id")

    private Pedido pedido;
    

    @Transient

    private Producto producto;
}
