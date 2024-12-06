package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.unam.ecomarket.modelo.DetallePedido;

/**
 * Repositorio que gestiona las operaciones CRUD para la entidad {@link DetallePedido}.
 * 
 * <p>
 * Extiende {@link JpaRepository} para proporcionar métodos predefinidos que permiten
 * realizar operaciones como guardar, buscar, actualizar y eliminar detalles de pedidos
 * en la base de datos.
 * </p>
 * 
 * <p>
 * Al heredar de JpaRepository, no es necesario implementar manualmente métodos comunes
 * como `findById`, `save` o `delete`.
 * </p>
 * 
 * <p>
 * Está marcado como un componente de Spring con la anotación {@code @Repository}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

}
