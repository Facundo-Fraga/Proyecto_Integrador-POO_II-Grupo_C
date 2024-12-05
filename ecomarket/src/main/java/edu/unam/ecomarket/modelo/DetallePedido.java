package edu.unam.ecomarket.modelo;


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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_pedido_seq")
@SequenceGenerator(name = "detalle_pedido_seq", sequenceName = "detalle_pedido_sequence", allocationSize = 1)
private Long idDetallePedido;


    @Column
    private int cantidad;
    
    @Column
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "producto_id_producto")
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        
        this.subTotal = calcularSubTotal(producto, cantidad);
    }

    private double calcularSubTotal(Producto producto, int cantidad){
        
        return producto.getPrecioFinal() * cantidad;   
    }
}
