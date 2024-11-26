package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unam.ecomarket.modelo.ProductoIndividual;

@Repository
public interface ProductoIndividualRepository extends JpaRepository<ProductoIndividual, Long> {

}
