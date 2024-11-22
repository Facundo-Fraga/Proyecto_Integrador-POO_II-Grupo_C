package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin") // Valor específico para esta subclase en la columna "tipo"

public class Administrador extends Usuario {

public Administrador(String nombre, String contrasena, String email) {
        super(nombre, contrasena, email);  
    }
}
