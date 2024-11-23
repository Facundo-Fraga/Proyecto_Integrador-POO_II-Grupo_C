package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "paquete")


@Getter
@Setter
@NoArgsConstructor

public class ProductoPaquete implements Producto{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paquete_seq")
    @SequenceGenerator(name = "paquete_seq", sequenceName = "paquete_sequence", allocationSize = 1)
    private Long idPaquete;

    @Column(name = "nombre", nullable = false)
    @NotBlank

    private String nombre;

    @Column(name = "descripcion", nullable = false)
    @NotBlank

    private String descripcion;

    @Column(name = "precioTotal", nullable = false)
    private double precioTotal;

    @Column(name = "disponible", nullable = false)
    private short disponible = 1;

    @ManyToMany(mappedBy = "listaPaquetes")
    private List<ProductoIndividual> productos;

    @Transient  

    private EstrategiaPrecio estrategiaPrecio;


    public ProductoPaquete(String nombre, String descripcion, double precioTotal,
            List<ProductoIndividual> productos, EstrategiaPrecio estrategiaPrecio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioTotal = precioTotal;
        this.productos = productos;
        this.estrategiaPrecio = estrategiaPrecio;
        
    }


    @Override
    public short esDisponible() {
        return disponible;
    }

   
    public void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio) {
        this.estrategiaPrecio = estrategiaPrecio;
    }




    @Override
    public double obtenerPrecio() {
        return estrategiaPrecio.calcularPrecio(precioTotal);
    }

  

}

