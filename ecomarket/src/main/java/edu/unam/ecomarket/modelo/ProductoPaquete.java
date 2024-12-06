package edu.unam.ecomarket.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un paquete de productos en el sistema.
 * 
 * <p>
 * Hereda de la clase {@link Producto} y permite agrupar varios productos
 * singulares en un paquete. El precio base del paquete se calcula automáticamente
 * como el 90% de la suma de los precios base de los productos incluidos.
 * </p>
 * 
 * <p>
 * Los productos que forman parte del paquete se almacenan en una relación
 * de muchos a muchos, gestionada mediante una tabla intermedia llamada
 * {@code paquete_producto}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProductoPaquete extends Producto {

    /**
     * Lista de productos singulares que forman parte del paquete.
     * 
     * <p>
     * Relación de muchos a muchos, gestionada mediante una tabla intermedia
     * {@code paquete_producto}.
     * </p>
     */
    @ManyToMany
    @JoinTable(
        name = "paquete_producto",
        joinColumns = @JoinColumn(name = "paquete_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoSingular> productos = new ArrayList<>();

    /**
     * Agrega un producto singular al paquete.
     * 
     * <p>
     * Si el producto no está ya en el paquete, se agrega y el precio base del paquete
     * se recalcula automáticamente.
     * </p>
     * 
     * @param producto Producto singular a agregar al paquete.
     */
    public void agregarProducto(ProductoSingular producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
            recalcularPrecioBase();
        }
    }

    /**
     * Elimina un producto del paquete.
     * 
     * <p>
     * Si el producto está en el paquete, se elimina y el precio base del paquete
     * se recalcula automáticamente.
     * </p>
     * 
     * @param producto Producto a eliminar del paquete.
     */
    public void eliminarProducto(Producto producto) {
        if (productos.remove(producto)) {
            recalcularPrecioBase();
        }
    }

    /**
     * Recalcula el precio base del paquete.
     * 
     * <p>
     * El precio base se calcula como el 90% de la suma de los precios base
     * de los productos que forman parte del paquete.
     * </p>
     */
    public void recalcularPrecioBase() {
        double sumaPrecios = productos.stream().mapToDouble(Producto::getPrecioBase).sum();
        precioBase = sumaPrecios * 0.9;
    }
}
