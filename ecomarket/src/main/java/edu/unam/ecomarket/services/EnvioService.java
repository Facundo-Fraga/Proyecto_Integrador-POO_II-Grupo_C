package edu.unam.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.repositories.EnvioRepository;

@Service
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository;
    
    public MetodoEnvio crearEnvio(MetodoEnvio metodoEnvio) {
        return envioRepository.save(metodoEnvio);
    }

}
