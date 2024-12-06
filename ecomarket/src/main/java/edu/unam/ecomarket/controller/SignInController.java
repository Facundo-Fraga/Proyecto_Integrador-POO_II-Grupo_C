package edu.unam.ecomarket.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Controlador encargado de gestionar el registro de nuevos usuarios (Clientes).
 * 
 * <p>
 * Este controlador permite mostrar el formulario de registro, validar los datos
 * ingresados y registrar un nuevo usuario en el sistema, incluyendo el cifrado
 * de la contraseña.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class SignInController {

    /**
     * Servicio para gestionar las operaciones relacionadas con los usuarios.
     */
    @Autowired
    private UsuarioService servicio;

    /**
     * Objeto para gestionar la sesión actual.
     */
    @Autowired
    private HttpSession session;

    /**
     * Constructor con inyección de dependencias para el servicio de usuarios.
     * 
     * @param servicio Servicio de usuarios inyectado.
     */
    public SignInController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    /**
     * Muestra el formulario para el registro de un nuevo usuario.
     * 
     * @param modelo Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para el formulario de registro.
     */
    @GetMapping("/signIn")
    public String index(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "signIn";
    }

    /**
     * Procesa el registro de un nuevo usuario (Cliente).
     * 
     * <p>
     * Valida los datos ingresados en el formulario, cifra la contraseña y registra
     * al usuario si no existen errores y el nombre de usuario no está en uso.
     * </p>
     * 
     * @param cliente   Objeto que representa al cliente a registrar.
     * @param resultado Objeto que contiene los errores de validación del formulario.
     * @param modelo    Modelo para pasar datos a la vista.
     * @return Redirección a la página de login si el registro es exitoso, o vuelve
     *         al formulario en caso de errores.
     */
    @PostMapping("/signIn")
    public String registrarUsuario(@Valid Cliente cliente, BindingResult resultado, Model modelo) {
        if (resultado.hasErrors()) {
            return "signIn";
        }

        // Verificar si el nombre de usuario ya está en uso
        if (servicio.buscarUsuarioPorNombre(cliente.getNombre()) == null) {
            // Validar la longitud de la contraseña
            if (cliente.getContrasenia().length() < 8) {
                modelo.addAttribute("error", "La contraseña debe tener al menos 8 caracteres");
                return "signIn";
            }

            // Cifrar la contraseña antes de guardarla
            cliente.setContrasenia(BCrypt.hashpw(cliente.getContrasenia(), BCrypt.gensalt()));
            servicio.cargarUsuario(cliente);
            return "redirect:/login";
        }

        modelo.addAttribute("error", "Ya existe una cuenta con ese Nombre de Usuario.");
        return "signIn";
    }
}
