package edu.unam.ecomarket.services;

/**
 * Excepción personalizada que se lanza cuando no se encuentra una entidad en el sistema.
 * 
 * <p>
 * Extiende {@link RuntimeException} para permitir el manejo de errores relacionados con
 * entidades no encontradas, como productos, descuentos, pedidos, etc.
 * </p>
 * 
 * <p>
 * Incluye el identificador de la entidad no encontrada en el mensaje de error.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
public class EntidadNoEncontradaExcepcion extends RuntimeException {

    /**
     * Constructor que inicializa la excepción con un mensaje que incluye el ID
     * de la entidad no encontrada.
     * 
     * @param id ID de la entidad que no se encontró.
     */
    public EntidadNoEncontradaExcepcion(Long id) {
        super("Entidad no encontrada: " + id);
    }
}
