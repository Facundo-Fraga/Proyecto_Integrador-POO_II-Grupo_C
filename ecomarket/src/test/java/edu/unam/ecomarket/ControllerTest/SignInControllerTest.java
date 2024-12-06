package edu.unam.ecomarket.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.SignInController;
import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.services.UsuarioService;

@WebMvcTest(SignInController.class)
public class SignInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    private MockHttpSession session;
    private Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
        clienteMock = new Cliente();
        clienteMock.setNombre("TestUser");
        clienteMock.setContrasenia("password123");
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/signIn"))
                .andExpect(status().isOk())
                .andExpect(view().name("signIn"))
                .andExpect(model().attributeExists("cliente"));
    }

    @Test
    public void testRegistrarUsuario_Success() throws Exception {
        when(usuarioService.buscarUsuarioPorNombre("TestUser")).thenReturn(null);

        mockMvc.perform(post("/signIn")
                .param("nombre", "TestUser")
                .param("contrasenia", "password123")
                .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(usuarioService, times(1)).cargarUsuario(any(Cliente.class));
    }

    @Test
    public void testRegistrarUsuario_ExistingUser() throws Exception {
        when(usuarioService.buscarUsuarioPorNombre("TestUser")).thenReturn(clienteMock);

        mockMvc.perform(post("/signIn")
                .param("nombre", "TestUser")
                .param("contrasenia", "password123")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("signIn"))
                .andExpect(model().attribute("error", "Ya existe una cuenta con ese Nombre de Usuario."));

        verify(usuarioService, never()).cargarUsuario(any(Cliente.class));
    }

    @Test
    public void testRegistrarUsuario_InvalidPassword() throws Exception {
        when(usuarioService.buscarUsuarioPorNombre("TestUser")).thenReturn(null);

        mockMvc.perform(post("/signIn")
                .param("nombre", "TestUser")
                .param("contrasenia", "short")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("signIn"))
                .andExpect(model().attribute("error", "La contraseña debe tener al menos 8 caracteres"));

        verify(usuarioService, never()).cargarUsuario(any(Cliente.class));
    }

    @Test
    public void testRegistrarUsuario_WithValidationErrors() throws Exception {
        mockMvc.perform(post("/signIn")
                .param("nombre", "")
                .param("contrasenia", "password123")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("signIn"))
                .andExpect(model().attributeHasFieldErrors("cliente", "nombre"));

        verify(usuarioService, never()).cargarUsuario(any(Cliente.class));
    }
}
