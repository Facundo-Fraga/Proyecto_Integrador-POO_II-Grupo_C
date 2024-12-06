package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unam.ecomarket.modelo.Usuario;

/**
 * Repositorio que gestiona las operaciones CRUD para la entidad {@link Usuario}.
 * 
 * <p>
 * Extiende {@link JpaRepository} para proporcionar métodos predefinidos que permiten
 * realizar operaciones como guardar, buscar, actualizar y eliminar usuarios en la base
 * de datos.
 * </p>
 * 
 * <p>
 * Incluye un método personalizado para buscar usuarios por su nombre.
 * </p>
 * 
 * <p>
 * Diseñado para manejar la jerarquía de herencia de {@link Usuario}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre.
     * 
     * <p>
     * Este método es útil para validar credenciales de inicio de sesión u operaciones
     * relacionadas con la identidad del usuario.
     * </p>
     * 
     * @param nombre Nombre del usuario a buscar.
     * @return Un objeto {@link Usuario} si se encuentra, o {@code null} si no existe.
     */
    Usuario findByNombre(String nombre);
}
