package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "producto")

@Getter
@Setter
@NoArgsConstructor

public class ProductoIndividual implements Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "producto_sequence", allocationSize = 1)
    private Long idProducto;

    @Column(nullable = false)
    @NotBlank

    private String nombre;

    @Column(nullable = false)
    @NotBlank

    private String descripcion;

    @Column(nullable = false)

    private Long cantidad;

    @Column(nullable = false)

    private CategoriaProducto categoria;

    @Column(nullable = false)

    private TipoUnidad unidad;

    @Column(nullable = false)
    private double precioBase;

    @Column(nullable = false)
    private double precioFinal;

    @Column(nullable = false)
    private double descuento = 0; // Sin descuento

    @Column(nullable = true)
    private LocalDateTime inicioDescuento = null;
    
    @Column(nullable = true)
    private LocalDateTime finDescuento = null;




    public ProductoIndividual(String nombre, String descripcion, Long cantidad, CategoriaProducto categoria,
            TipoUnidad unidad, double precioBase) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.unidad = unidad;
        this.precioBase = precioBase;
    }

   

  
    


    @Override
    public double obtenerPrecio() {
        return estrategiaPrecio.calcularPrecio(precioBase);
    }

}



