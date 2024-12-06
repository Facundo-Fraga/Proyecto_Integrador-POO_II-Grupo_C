package edu.unam.ecomarket.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un Descuento en el sistema.
 * 
 * <p>
 * Los descuentos se aplican a productos en función de una estrategia definida.
 * Cada descuento tiene un nombre, un rango de fechas de validez y una estrategia
 * de descuento asociada.
 * </p>
 * 
 * <p>
 * La relación entre los descuentos y los productos es de muchos a muchos.
 * Los descuentos se gestionan mediante estrategias que pueden ser porcentajes
 * o valores fijos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Table(name = "descuentos")
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Descuento {

    /**
     * Identificador único del descuento.
     * Generado automáticamente mediante una secuencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "descuento_seq")
    @SequenceGenerator(name = "descuento_seq", sequenceName = "descuento_sequence", allocationSize = 1)
    private Long idDescuento;

    /**
     * Nombre del descuento.
     * Este campo no puede estar vacío.
     */
    @Column(nullable = false)
    @NotBlank
    private String nombre;

    /**
     * Fecha de inicio del descuento.
     */
    @Column(nullable = false)
    private LocalDate fechaInicio;

    /**
     * Fecha de finalización del descuento.
     */
    @Column(nullable = false)
    private LocalDate fechaFin;

    /**
     * Estrategia asociada al descuento.
     * Define cómo se calculará el descuento.
     */
    @ManyToOne
    @JoinColumn(name = "estrategia_id", nullable = false)
    private EstrategiaDescuento estrategia;

    /**
     * Lista de productos a los que se aplica este descuento.
     * Relación bidireccional con la entidad Producto.
     */
    @ManyToMany(mappedBy = "descuentos")
    @Setter(AccessLevel.NONE)
    private List<Producto> productos;

    /**
     * Asocia una lista de productos al descuento, asegurándose de actualizar
     * la relación bidireccional.
     * 
     * @param productos Lista de productos a asociar.
     * @return {@code true} si la asociación fue exitosa, {@code false} en caso de error.
     */
    public boolean setProductos(List<Producto> productos) {
        try {
            List<Producto> copiaProductos = productos;
            for (Producto index : copiaProductos) {
                index.getDescuentos().add(this);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica si el descuento es aplicable en la fecha actual.
     * 
     * @return {@code true} si el descuento está dentro del rango de fechas, {@code false} de lo contrario.
     */
    public boolean esAplicable() {
        LocalDate hoy = LocalDate.now();
        return (hoy.isEqual(fechaInicio) || hoy.isAfter(fechaInicio)) &&
               (hoy.isBefore(fechaFin) || hoy.isEqual(fechaFin));
    }

    /**
     * Aplica el descuento al precio proporcionado en función de la estrategia asociada.
     * 
     * @param precio Precio original al que se aplicará el descuento.
     * @return Precio final después de aplicar el descuento.
     */
    public Double aplicarDescuento(Double precio) {
        if (estrategia instanceof DescuentoPorcentaje) {
            return estrategia.aplicarDescuento(precio);
        }
        if (estrategia instanceof DescuentoFijo) {
            return estrategia.aplicarDescuento(precio);
        }
        return 0.0;
    }

    /**
     * Obtiene el valor del descuento definido en la estrategia.
     * 
     * @return Valor del descuento como porcentaje o monto fijo.
     */
    public Double getValorDescuento() {
        return estrategia.getValorDescuento();
    }
}
