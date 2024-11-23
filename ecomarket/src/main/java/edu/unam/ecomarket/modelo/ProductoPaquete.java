package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    private BigDecimal precioTotal;
    
    @ManyToMany(mappedBy = "listaPaquetes")
    private List<ProductoIndividual> productos;


    public ProductoPaquete(String nombre, String descripcion, BigDecimal precioTotal,
            List<ProductoIndividual> productos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioTotal = precioTotal;
        this.productos = productos;
    }


    @Override
    public void aplicarDescuento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aplicarDescuento'");
    }

    @Override
    public short esDisponible() {
        return disponible;
    }

    @Override
    public void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstrategiaPrecio'");
    }

  

}
