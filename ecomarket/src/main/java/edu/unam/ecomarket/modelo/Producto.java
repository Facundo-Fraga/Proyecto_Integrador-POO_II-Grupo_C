package edu.unam.ecomarket.modelo;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "producto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto")
@Getter @Setter
@NoArgsConstructor
public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "producto_sequence", allocationSize = 1)
    Long idProducto;

    @Column(nullable = false)
    @NotBlank
    protected String nombre;

    @Column(nullable = false)
    @NotBlank
    protected String descripcion;

    @ElementCollection
    @CollectionTable(
        name = "producto_detalles",
        joinColumns = @JoinColumn(name = "producto_id")
    )
    @MapKeyColumn(name = "clave")
    @Column(name = "valor")
    protected Map<String, String> detalles = new HashMap<>();
    
    @Column(nullable = false)
    @NotNull
    protected double precio;

    public String getDetalle(String detalle) {
        return detalles.get(detalle);
    }

    abstract void aplicarDescuento(double descuento);
}
