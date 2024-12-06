package edu.unam.ecomarket.modelo;

import jakarta.persistence.Entity;

/**
 * Clase que representa a un Cliente en el sistema.
 * 
 * <p>
 * Hereda de la clase {@link Usuario} y está mapeada como una entidad en la base de datos.
 * Los clientes son los usuarios finales que interactúan con el sistema para realizar compras,
 * gestionar pedidos y acceder a los productos disponibles.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
public class Cliente extends Usuario {
    // Clase vacía que hereda las propiedades y comportamientos de Usuario
}
