package edu.unam.ecomarket.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoSingular;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    @Query("SELECT p FROM Producto p WHERE TYPE(p) = ProductoSingular")
    List<ProductoSingular> findAllProductoSingular();

    @Query("SELECT p FROM Producto p WHERE TYPE(p) = ProductoSingular AND p.id = :id")
    Optional<ProductoSingular> findProductoSingularById(@Param("id") Long id);
}
