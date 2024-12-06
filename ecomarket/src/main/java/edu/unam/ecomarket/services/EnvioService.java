package edu.unam.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.repositories.EnvioRepository;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los métodos de envío.
 * 
 * <p>
 * Proporciona métodos para crear y gestionar envíos en el sistema.
 * Se apoya en el repositorio {@link EnvioRepository} para interactuar con la base de datos.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    /**
     * Crea un nuevo método de envío y lo guarda en la base de datos.
     * 
     * @param metodoEnvio El método de envío a crear.
     * @return El método de envío guardado, con su ID generado.
     */
    public MetodoEnvio crearEnvio(MetodoEnvio metodoEnvio) {
        return envioRepository.save(metodoEnvio);
    }
}
