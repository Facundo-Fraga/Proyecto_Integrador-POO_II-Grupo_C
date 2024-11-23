package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unam.ecomarket.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
