package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unam.ecomarket.modelo.Descuento;

/**
 * Repositorio que gestiona las operaciones CRUD para la entidad {@link Descuento}.
 * 
 * <p>
 * Extiende {@link JpaRepository} para proporcionar métodos predefinidos que permiten
 * realizar operaciones como guardar, buscar, actualizar y eliminar descuentos en la base
 * de datos.
 * </p>
 * 
 * <p>
 * Al heredar de JpaRepository, no es necesario implementar manualmente métodos comunes
 * como `findById` o `save`.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
public interface DescuentosRepository extends JpaRepository<Descuento, Long> {

}
