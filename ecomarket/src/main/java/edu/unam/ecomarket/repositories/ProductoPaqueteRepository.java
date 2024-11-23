package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unam.ecomarket.modelo.ProductoPaquete;

@Repository
public interface ProductoPaqueteRepository extends JpaRepository<ProductoPaquete, Long> {

}
