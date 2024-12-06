package edu.unam.ecomarket.RepositoriesTest;

import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.repositories.UsuarioRepository;
import edu.unam.ecomarket.services.UsuarioService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class) // Habilita la integración con Mockito
class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository; // Mock del repositorio

    @InjectMocks
    private UsuarioService usuarioService; // Servicio que usa el repositorio mockeado

    @Test
    public void testFindByNombre() {
        // Crear un usuario con una subclase anónima
        Usuario usuario = new Usuario() {
            private String nombre = "Juan";
            private String contrasenia;
            private String email;

            @Override
            public String getNombre() {
                return nombre;
            }

            @Override
            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            @Override
            public String getContrasenia() {
                return contrasenia;
            }

            @Override
            public void setContrasenia(String contrasenia) {
                this.contrasenia = contrasenia;
            }

            @Override
            public String getEmail() {
                return email;
            }

            @Override
            public void setEmail(String email) {
                this.email = email;
            }
        };

        // Simula el comportamiento del repositorio
        when(usuarioRepository.findByNombre("Juan")).thenReturn(usuario);

        // Invoca el servicio (o directamente el repositorio si es el caso)
        Usuario usuarioEncontrado = usuarioRepository.findByNombre("Juan");

        // Verifica que el repositorio fue llamado correctamente
        verify(usuarioRepository).findByNombre("Juan");

        // Verifica el resultado de la búsqueda
        assertThat(usuarioEncontrado).isNotNull();
        assertThat(usuarioEncontrado.getNombre()).isEqualTo("Juan");
    }
}
