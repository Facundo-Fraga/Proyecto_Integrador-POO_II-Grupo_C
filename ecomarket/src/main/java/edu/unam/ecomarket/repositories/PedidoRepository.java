package edu.unam.ecomarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.unam.ecomarket.modelo.Pedido;

/**
 * Repositorio que gestiona las operaciones CRUD para la entidad {@link Pedido}.
 * 
 * <p>
 * Extiende {@link JpaRepository} para proporcionar métodos predefinidos que permiten
 * realizar operaciones como guardar, buscar, actualizar y eliminar pedidos en la base
 * de datos.
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
 * <p>
 * Diseñado para soportar consultas personalizadas relacionadas con pedidos en futuras iteraciones.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
