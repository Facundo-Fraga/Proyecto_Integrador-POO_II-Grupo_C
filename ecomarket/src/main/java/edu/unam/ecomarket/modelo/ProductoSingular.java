package edu.unam.ecomarket.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un producto singular en el sistema.
 * 
 * <p>
 * Hereda de la clase {@link Producto} y se utiliza para modelar productos individuales
 * que pueden ser incluidos en uno o más paquetes. Además, permite almacenar detalles
 * específicos del producto en forma de pares clave-valor.
 * </p>
 * 
 * <p>
 * La relación con los paquetes es de muchos a muchos, gestionada mediante una tabla
 * intermedia llamada {@code paquete_producto}.
 * </p>
 * 
 * <p>
 * Los detalles del producto se almacenan como un mapa de claves y valores, utilizando
 * una tabla adicional llamada {@code producto_detalles}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProductoSingular extends Producto {

    /**
     * Lista de paquetes en los que este producto singular está incluido.
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
    private List<ProductoPaquete> paquetesContenedores = new ArrayList<>();

    /**
     * Detalles específicos del producto, almacenados como pares clave-valor.
     * 
     * <p>
     * Utiliza una colección embebida mapeada en una tabla adicional
     * {@code producto_detalles}.
     * </p>
     */
    @ElementCollection
    @CollectionTable(
        name = "producto_detalles",
        joinColumns = @JoinColumn(name = "producto_id")
    )
    @MapKeyColumn(name = "clave")
    @Column(name = "valor")
    protected Map<String, String> detalles = new HashMap<>();

    /**
     * Agrega un paquete contenedor a este producto singular y actualiza la relación bidireccional.
     * 
     * @param paquete El paquete contenedor a agregar.
     */
    public void agregarPaqueteContenedor(ProductoPaquete paquete) {
        paquetesContenedores.add(paquete);
        paquete.agregarProducto(this);
    }

    /**
     * Elimina un paquete contenedor de este producto singular y actualiza la relación bidireccional.
     * 
     * @param paquete El paquete contenedor a eliminar.
     */
    public void eliminarPaqueteContenedor(ProductoPaquete paquete) {
        paquetesContenedores.remove(paquete);
        paquete.eliminarProducto(this);
    }

    /**
     * Obtiene la lista de paquetes en los que este producto singular está incluido.
     * 
     * @return Lista de paquetes contenedores.
     */
    public List<ProductoPaquete> getPaquetesContenedores() {
        return paquetesContenedores;
    }
}
