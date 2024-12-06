package edu.unam.ecomarket.modelo;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase abstracta que representa un producto en el sistema.
 * 
 * <p>
 * Esta clase es la base para los diferentes tipos de productos (por ejemplo,
 * productos singulares y paquetes de productos) y define atributos y métodos comunes.
 * Está diseñada para ser utilizada con herencia en JPA, almacenando todos los tipos de
 * productos en una única tabla diferenciada por la columna {@code tipo_producto}.
 * </p>
 * 
 * <p>
 * Los productos pueden tener descuentos asociados, los cuales se aplican para calcular
 * el precio final si están vigentes.
 * </p>
 * 
 * <p>
 * Relaciona productos con descuentos mediante una tabla intermedia llamada
 * {@code producto_descuento}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@Table(name = "producto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto")
@Getter
@Setter
@NoArgsConstructor
public abstract class Producto {

    /**
     * Identificador único del producto.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "producto_sequence", allocationSize = 1)
    private Long idProducto;

    /**
     * Nombre del producto.
     */
    @Column(nullable = false)
    @NotNull
    protected String nombre;

    /**
     * Descripción del producto.
     */
    @Column(nullable = false)
    @NotNull
    protected String descripcion;

    /**
     * Precio base del producto, antes de aplicar descuentos.
     */
    @Column(nullable = false)
    @NotNull
    protected double precioBase;

    /**
     * Lista de descuentos aplicables al producto.
     * 
     * <p>
     * La relación es de muchos a muchos, gestionada mediante una tabla intermedia
     * {@code producto_descuento}.
     * </p>
     */
    @ManyToMany
    @JoinTable(
        name = "producto_descuento",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "descuento_id")
    )
    private List<Descuento> descuentos;

    /**
     * Verifica si el producto tiene algún descuento aplicable actualmente.
     * 
     * @return {@code true} si hay descuentos aplicables, {@code false} de lo contrario.
     */
    public Boolean tieneDescuento() {
        if (descuentos == null || descuentos.isEmpty()) {
            return false;
        }
        return descuentos.stream().anyMatch(Descuento::esAplicable);
    }

    /**
     * Calcula el precio final del producto después de aplicar los descuentos vigentes.
     * 
     * <p>
     * Si hay múltiples descuentos aplicables, se aplican en el orden en que aparecen
     * en la lista de descuentos.
     * </p>
     * 
     * @return El precio final del producto, después de aplicar los descuentos.
     */
    public double getPrecioFinal() {
        Double precioFinal = this.precioBase;

        if (descuentos != null) {
            for (Descuento index : descuentos) {
                if (index.esAplicable()) {
                    precioFinal = index.aplicarDescuento(precioFinal);
                }
            }
        }

        return precioFinal;
    }
}
