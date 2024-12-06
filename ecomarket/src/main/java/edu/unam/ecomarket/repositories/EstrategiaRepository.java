package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;

/**
 * Repositorio que gestiona las operaciones CRUD para la entidad {@link EstrategiaDescuento}.
 * 
 * <p>
 * Extiende {@link JpaRepository} para proporcionar métodos predefinidos que permiten
 * realizar operaciones como guardar, buscar, actualizar y eliminar estrategias de descuento
 * en la base de datos.
 * </p>
 * 
 * <p>
 * Al heredar de JpaRepository, no es necesario implementar manualmente métodos comunes
 * como `findById`, `save` o `delete`.
 * </p>
 * 
 * <p>
 * Este repositorio no requiere anotación {@code @Repository} explícita, ya que
 * Spring detecta automáticamente las interfaces que extienden `JpaRepository` como
 * componentes de repositorio.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
public interface EstrategiaRepository extends JpaRepository<EstrategiaDescuento, Long> {

}
