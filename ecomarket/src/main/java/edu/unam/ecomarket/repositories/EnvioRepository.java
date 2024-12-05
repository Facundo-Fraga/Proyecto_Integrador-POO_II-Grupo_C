package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unam.ecomarket.modelo.MetodoEnvio;

@Repository
public interface EnvioRepository extends JpaRepository<MetodoEnvio, Long> {


}
