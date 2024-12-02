package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unam.ecomarket.modelo.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {


}

