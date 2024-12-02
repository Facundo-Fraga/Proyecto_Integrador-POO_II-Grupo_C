package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unam.ecomarket.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
