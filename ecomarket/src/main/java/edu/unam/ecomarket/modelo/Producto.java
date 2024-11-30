package edu.unam.ecomarket.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
    
    @Column(nullable = false)
    @NotNull
    protected double precioBase = 0;

    @Column(nullable = false)
    @NotNull
    protected double porcentajeDescuento = 0;

    public double getPrecioFinal() {
        return getPrecioBase() * (1 - porcentajeDescuento / 100);
    }

    public boolean tieneDescuento() {
        return porcentajeDescuento > 0;
    }
}