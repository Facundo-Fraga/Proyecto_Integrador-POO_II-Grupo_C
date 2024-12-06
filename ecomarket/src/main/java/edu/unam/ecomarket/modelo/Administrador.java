package edu.unam.ecomarket.modelo;

import jakarta.persistence.Entity;

/**
 * Clase que representa a un Administrador en el sistema.
 * 
 * <p>
 * Hereda de la clase {@link Usuario} y está mapeada como una entidad en la base de datos.
 * Los administradores tienen privilegios adicionales en el sistema, como la gestión de usuarios y productos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Entity
public class Administrador extends Usuario {
    // Clase vacía que hereda las propiedades y comportamientos de Usuario
}
