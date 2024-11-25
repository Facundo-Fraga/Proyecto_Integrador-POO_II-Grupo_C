package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unam.ecomarket.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
