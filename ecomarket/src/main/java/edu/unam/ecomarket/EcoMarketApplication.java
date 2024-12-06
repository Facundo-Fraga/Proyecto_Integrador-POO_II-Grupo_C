package edu.unam.ecomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación EcoMarket.
 * Esta clase se encarga de iniciar la aplicación utilizando Spring Boot.
 * 
 * <p>
 * EcoMarket es un proyecto diseñado para facilitar la gestión de un mercado en línea
 * promoviendo el comercio sustentable.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@SpringBootApplication
public class EcoMarketApplication {

    /**
     * Método principal que arranca la aplicación.
     * Utiliza el método estático {@code run()} de {@code SpringApplication}
     * para iniciar el contexto de Spring Boot.
     * 
     * @param args Parámetros opcionales que se pueden pasar al momento de ejecutar la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(EcoMarketApplication.class, args);
    }
}
