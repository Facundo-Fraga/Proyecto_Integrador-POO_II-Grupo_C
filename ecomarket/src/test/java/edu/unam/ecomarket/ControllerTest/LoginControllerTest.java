/*package edu.unam.ecomarket.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.LoginController;
import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.services.CarritoService;
import edu.unam.ecomarket.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private CarritoService carritoService;

    private Usuario usuarioCliente;
    private Usuario usuarioAdmin;

    // Subclase concreta para el test
    private static class UsuarioTest extends Usuario {
        public UsuarioTest(Long id, String nombre, String contrasenia) {
            this.setNombre(nombre);
            this.setContrasenia(contrasenia);
        }
    }

    @BeforeEach
    public void setUp() {
        // Configuración de usuarios de prueba
        usuarioCliente = new UsuarioTest(1L, "clienteTest", BCrypt.hashpw("password123", BCrypt.gensalt()));
        usuarioAdmin = new UsuarioTest(2L, "adminTest", BCrypt.hashpw("adminpass", BCrypt.gensalt()));
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
public void testIniciarSesionClienteCorrecto() throws Exception {
    HttpSession mockSession = mock(HttpSession.class);

    // Usar un objeto de tipo Cliente en lugar de Usuario
    Usuario clienteTest = new Cliente();
    clienteTest.setNombre("clienteTest");
    clienteTest.setContrasenia(BCrypt.hashpw("password123", BCrypt.gensalt()));

    when(usuarioService.buscarUsuarioPorNombre("clienteTest")).thenReturn(clienteTest);

    mockMvc.perform(post("/login")
            .param("nombre", "clienteTest")
            .param("contrasenia", "password123")
            .sessionAttr("session", mockSession))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/clientMenu"));

     verify(mockSession, times(1)).setAttribute("clienteActual", clienteTest);
    verify(carritoService, times(1)).vaciarCarrito();
}


    @Test
    public void testIniciarSesionAdminCorrecto() throws Exception {
        HttpSession mockSession = mock(HttpSession.class);

        when(usuarioService.buscarUsuarioPorNombre("adminTest")).thenReturn(usuarioAdmin);

        mockMvc.perform(post("/login")
                .param("nombre", "adminTest")
                .param("contrasenia", "adminpass")
                .sessionAttr("session", mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mainMenu"));

        verify(mockSession, never()).setAttribute(eq("clienteActual"), any(Usuario.class));
        verify(carritoService, never()).vaciarCarrito();
    }

    @Test
    public void testIniciarSesionCredencialesIncorrectas() throws Exception {
        HttpSession mockSession = mock(HttpSession.class);

        when(usuarioService.buscarUsuarioPorNombre("clienteTest")).thenReturn(usuarioCliente);

        mockMvc.perform(post("/login")
                .param("nombre", "clienteTest")
                .param("contrasenia", "wrongPassword")
                .sessionAttr("session", mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Nombre de usuario o contraseña incorrectos."));
    }

    @Test
    public void testIniciarSesionUsuarioNoExistente() throws Exception {
        HttpSession mockSession = mock(HttpSession.class);

        when(usuarioService.buscarUsuarioPorNombre("nonExistentUser")).thenReturn(null);

        mockMvc.perform(post("/login")
                .param("nombre", "nonExistentUser")
                .param("contrasenia", "password123")
                .sessionAttr("session", mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Nombre de usuario o contraseña incorrectos."));
    }

    @Test
    public void testCerrarSesion() throws Exception {
        HttpSession mockSession = mock(HttpSession.class);

        mockMvc.perform(get("/logout").sessionAttr("session", mockSession))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

        verify(mockSession, times(1)).invalidate();
    }
}
*/
package edu.unam.ecomarket.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.LoginController;
import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.services.CarritoService;
import edu.unam.ecomarket.services.UsuarioService;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private CarritoService carritoService;

    private Usuario usuarioCliente;
    private Usuario usuarioAdmin;

    // Subclase concreta para el test
    private static class UsuarioTest extends Usuario {
        public UsuarioTest(Long id, String nombre, String contrasenia, String email) {
            this.setIdUsuario(id);
            this.setNombre(nombre);
            this.setContrasenia(contrasenia);
            this.setEmail(email);
        }
    }

    @BeforeEach
    public void setUp() {
        // Configuración de usuarios de prueba
        usuarioCliente = new UsuarioTest(1L, "clienteTest", BCrypt.hashpw("password123", BCrypt.gensalt()), "cliente@test.com");
        usuarioAdmin = new UsuarioTest(2L, "adminTest", BCrypt.hashpw("adminpass", BCrypt.gensalt()), "admin@test.com");
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testIniciarSesionClienteCorrecto() throws Exception {
        // Configurar el mock del servicio
        when(usuarioService.buscarUsuarioPorNombre("clienteTest")).thenReturn(usuarioCliente);

        // Realizar la solicitud de prueba
        mockMvc.perform(post("/login")
                .param("nombre", "clienteTest")
                .param("contrasenia", "password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clientMenu"));

        // Verificar interacciones
        verify(carritoService, times(1)).vaciarCarrito();
    }

    @Test
    public void testIniciarSesionAdminCorrecto() throws Exception {
        // Configurar el mock del servicio
        when(usuarioService.buscarUsuarioPorNombre("adminTest")).thenReturn(usuarioAdmin);

        // Realizar la solicitud de prueba
        mockMvc.perform(post("/login")
                .param("nombre", "adminTest")
                .param("contrasenia", "adminpass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mainMenu"));

        // Verificar interacciones
        verify(carritoService, never()).vaciarCarrito();
    }

    @Test
    public void testIniciarSesionCredencialesIncorrectas() throws Exception {
        // Configurar el mock del servicio
        when(usuarioService.buscarUsuarioPorNombre("clienteTest")).thenReturn(usuarioCliente);

        // Realizar la solicitud de prueba
        mockMvc.perform(post("/login")
                .param("nombre", "clienteTest")
                .param("contrasenia", "wrongPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Nombre de usuario o contraseña incorrectos."));
    }

    @Test
    public void testIniciarSesionUsuarioNoExistente() throws Exception {
        // Configurar el mock del servicio para devolver null
        when(usuarioService.buscarUsuarioPorNombre("nonExistentUser")).thenReturn(null);

        // Realizar la solicitud de prueba
        mockMvc.perform(post("/login")
                .param("nombre", "nonExistentUser")
                .param("contrasenia", "password123"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Nombre de usuario o contraseña incorrectos."));
    }

    @Test
    public void testCerrarSesion() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
