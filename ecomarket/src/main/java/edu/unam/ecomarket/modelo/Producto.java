package edu.unam.ecomarket.modelo;


import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
    @NotNull
    protected String nombre;

    @Column(nullable = false)
    @NotNull
    protected String descripcion;
    
    @Column(nullable = false)
    @NotNull
    protected double precioBase;

    @ManyToMany
    @JoinTable(
        name = "producto_descuento",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "descuento_id")
    )
    private List<Descuento> descuentos;

    public Boolean tieneDescuento() {
        if(descuentos == null || descuentos.isEmpty()) {
            return false;
        }
        return descuentos.stream().anyMatch(Descuento::esAplicable);
    }

    public double getPrecioFinal() {
        Double precioFinal = this.precioBase;

        if(descuentos != null) {
            for (Descuento index : descuentos) {
                if (index.esAplicable()) {
                    precioFinal = index.aplicarDescuento(precioFinal);
                }
            }
        }

        return precioFinal;
    }
}