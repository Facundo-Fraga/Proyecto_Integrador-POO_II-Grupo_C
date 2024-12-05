package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unam.ecomarket.modelo.Descuento;

public interface DescuentosRepository extends JpaRepository<Descuento, Long> {

}
