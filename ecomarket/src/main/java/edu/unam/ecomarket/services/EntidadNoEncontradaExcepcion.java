package edu.unam.ecomarket.services;

public class EntidadNoEncontradaExcepcion extends RuntimeException {

    EntidadNoEncontradaExcepcion(Long id) {
        super("Entidad no encontrada: " + id);
    }

}
