package edu.unam.ecomarket.modeloTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.unam.ecomarket.modelo.Usuario;

class UsuarioTest {

    // Subclase concreta de Usuario para propósitos de prueba
    public class UsuarioConcreto extends Usuario {
            public UsuarioConcreto(String nombre, String contrasenia, String email) {
            this.setNombre(nombre);
            this.setContrasenia(contrasenia);
            this.setEmail(email);
        }
    }

    @Test
    void testUsuarioAttributes() {
        // Configura un usuario de prueba
        Usuario usuario = new UsuarioConcreto("Juan Perez", "password123", "juan.perez@example.com");

        // Verifica que los atributos sean correctos
        assertNotNull(usuario.getNombre(), "El nombre no debería ser nulo");
        assertNotNull(usuario.getContrasenia(), "La contraseña no debería ser nula");
        assertNotNull(usuario.getEmail(), "El email no debería ser nulo");

        assertEquals("Juan Perez", usuario.getNombre(), "El nombre no coincide");
        assertEquals("password123", usuario.getContrasenia(), "La contraseña no coincide");
        assertEquals("juan.perez@example.com", usuario.getEmail(), "El email no coincide");
    }

    @Test
    void testSettersAndGetters() {
        // Configura un usuario de prueba
        Usuario usuario = new UsuarioConcreto("Juan Perez", "password123", "juan.perez@example.com");

        // Cambia valores usando setters
        usuario.setNombre("Maria Lopez");
        usuario.setContrasenia("newpassword456");
        usuario.setEmail("maria.lopez@example.com");

        // Verifica que los setters hayan actualizado correctamente
        assertEquals("Maria Lopez", usuario.getNombre(), "El nombre no se actualizó correctamente");
        assertEquals("newpassword456", usuario.getContrasenia(), "La contraseña no se actualizó correctamente");
        assertEquals("maria.lopez@example.com", usuario.getEmail(), "El email no se actualizó correctamente");
    }
}
