package edu.unam.ecomarket.ServicioTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.repositories.UsuarioRepository;
import edu.unam.ecomarket.services.UsuarioService;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para cargar un nuevo usuario
    @Test
    void testCargarUsuario() {
        Usuario usuarioMock = mock(Usuario.class);

        usuarioService.cargarUsuario(usuarioMock);

        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    // Test para buscar todos los usuarios
    @Test
    void testBuscarUsuarios() {
        List<Usuario> usuariosMock = List.of(mock(Usuario.class), mock(Usuario.class));

        when(usuarioRepository.findAll()).thenReturn(usuariosMock);

        List<Usuario> usuarios = usuarioService.buscarUsuarios();

        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    // Test para buscar un usuario por nombre
    @Test
    void testBuscarUsuarioPorNombre() {
        Usuario usuarioMock = mock(Usuario.class);
        when(usuarioRepository.findByNombre("UsuarioTest")).thenReturn(usuarioMock);

        Usuario usuario = usuarioService.buscarUsuarioPorNombre("UsuarioTest");

        assertNotNull(usuario);
        verify(usuarioRepository, times(1)).findByNombre("UsuarioTest");
    }

    // Test para obtener el cliente en sesión
    @Test
    void testObtenerClienteEnSesion() {
        Cliente clienteMock = mock(Cliente.class);
        when(session.getAttribute("clienteActual")).thenReturn(clienteMock);

        Cliente cliente = usuarioService.obtenerClienteEnSesion(session);

        assertNotNull(cliente);
        assertEquals(clienteMock, cliente);
        verify(session, times(1)).getAttribute("clienteActual");
    }

    // Test para obtener el cliente en sesión sin cliente autenticado
    @Test
    void testObtenerClienteEnSesionSinCliente() {
        when(session.getAttribute("clienteActual")).thenReturn(null);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            usuarioService.obtenerClienteEnSesion(session);
        });

        assertEquals("No hay un cliente autenticado en la sesión.", exception.getMessage());
        verify(session, times(1)).getAttribute("clienteActual");
    }

    // Test para editar un usuario existente
    @Test
    void testEditarUsuario() {
        Usuario usuarioExistente = mock(Usuario.class);
        Usuario usuarioEditado = mock(Usuario.class);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(usuarioExistente)).thenReturn(usuarioExistente);

        when(usuarioEditado.getIdUsuario()).thenReturn(1L);
        when(usuarioEditado.getNombre()).thenReturn("NuevoNombre");
        when(usuarioEditado.getContrasenia()).thenReturn("NuevaContrasenia");
        when(usuarioEditado.getEmail()).thenReturn("nuevo@email.com");

        Usuario resultado = usuarioService.editarUsuario(usuarioEditado);

        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuarioExistente);
    }

    // Test para editar un usuario inexistente
    @Test
    void testEditarUsuarioInexistente() {
        Usuario usuarioEditado = mock(Usuario.class);
        when(usuarioEditado.getIdUsuario()).thenReturn(99L);
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.editarUsuario(usuarioEditado);
        });

        assertEquals("Usuario no encontrado con el ID proporcionado.", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(99L);
        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }
}
