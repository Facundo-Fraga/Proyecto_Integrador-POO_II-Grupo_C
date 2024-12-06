package edu.unam.ecomarket.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;

/**
 * Repositorio que gestiona las operaciones CRUD para la entidad {@link Producto}.
 * 
 * <p>
 * Extiende {@link JpaRepository} para proporcionar métodos predefinidos que permiten
 * realizar operaciones como guardar, buscar, actualizar y eliminar productos en la base
 * de datos.
 * </p>
 * 
 * <p>
 * Incluye consultas personalizadas para filtrar productos por su tipo
 * ({@link ProductoSingular} o {@link ProductoPaquete}).
 * </p>
 * 
 * <p>
 * Diseñado para manejar la jerarquía de herencia de {@link Producto}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Obtiene todos los productos que son de tipo {@link ProductoSingular}.
     * 
     * @return Una lista de todos los productos singulares.
     */
    @Query("SELECT p FROM Producto p WHERE TYPE(p) = ProductoSingular")
    List<ProductoSingular> findAllProductoSingular();

    /**
     * Busca un producto singular por su ID.
     * 
     * @param id ID del producto singular a buscar.
     * @return Un {@link Optional} que contiene el producto singular, si se encuentra.
     */
    @Query("SELECT p FROM Producto p WHERE TYPE(p) = ProductoSingular AND p.id = :id")
    Optional<ProductoSingular> findProductoSingularById(@Param("id") Long id);

    /**
     * Busca un paquete de productos por su ID.
     * 
     * @param id ID del paquete de productos a buscar.
     * @return Un {@link Optional} que contiene el paquete de productos, si se encuentra.
     */
    @Query("SELECT p FROM Producto p WHERE TYPE(p) = ProductoPaquete AND p.id = :id")
    Optional<ProductoPaquete> findPaqueteById(@Param("id") Long id);
}
