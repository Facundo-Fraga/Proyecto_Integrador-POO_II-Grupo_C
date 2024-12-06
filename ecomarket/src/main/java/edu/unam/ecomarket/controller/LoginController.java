package edu.unam.ecomarket.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.services.CarritoService;
import edu.unam.ecomarket.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;

/**
 * Controlador encargado de gestionar las operaciones de inicio y cierre de sesión.
 * 
 * <p>
 * Este controlador se encarga de autenticar a los usuarios, manejar su sesión y
 * redirigirlos a las páginas correspondientes según su tipo (cliente, administrador, etc.).
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
@NoArgsConstructor
public class LoginController {

    /**
     * Servicio para gestionar las operaciones relacionadas con usuarios.
     */
    private UsuarioService servicio;

    /**
     * Objeto para manejar la sesión actual.
     */
    @Autowired
    private HttpSession session;

    /**
     * Servicio para gestionar el carrito de compras.
     */
    @Autowired
    private CarritoService carritoService;

    /**
     * Constructor con inyección de dependencias para el servicio de usuarios.
     * 
     * @param servicio Servicio de usuarios inyectado.
     */
    @Autowired
    public LoginController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    /**
     * Maneja las solicitudes GET para la página de inicio y login.
     * 
     * @return Nombre de la plantilla HTML para la página de login.
     */
    @GetMapping({ "/", "/login" })
    public String index() {
        return "login"; // Redirige a la página de login
    }

    /**
     * Procesa la solicitud POST para iniciar sesión.
     * 
     * <p>
     * Valida las credenciales del usuario, inicia sesión si son correctas y redirige
     * al menú correspondiente según el tipo de usuario. Si las credenciales son
     * incorrectas, muestra un mensaje de error.
     * </p>
     * 
     * @param nombre      Nombre de usuario ingresado.
     * @param contrasenia Contraseña ingresada.
     * @param modelo      Modelo para pasar datos a la vista.
     * @return Redirección a la página correspondiente o la vista de login con error.
     */
    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam("nombre") String nombre,
            @RequestParam("contrasenia") String contrasenia,
            Model modelo) {

        // Buscar al usuario por nombre
        Usuario encontrado = servicio.buscarUsuarioPorNombre(nombre);
        if (encontrado != null && BCrypt.checkpw(contrasenia, encontrado.getContrasenia())) {
            // Si es un Cliente, lo guardamos en la sesión
            if (encontrado instanceof Cliente) {
                session.setAttribute("clienteActual", (Cliente) encontrado);
                // Vaciar el carrito
                carritoService.vaciarCarrito();
                return "redirect:/clientMenu"; // Redirige al menú del cliente
            }
            // Si es otro tipo de usuario, redirige al menú principal (por ejemplo, administrador)
            return "redirect:/mainMenu";
        }

        // Si las credenciales son incorrectas, mostramos el error
        modelo.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
        return "login";
    }

    /**
     * Maneja la solicitud GET para cerrar sesión.
     * 
     * <p>
     * Invalida la sesión actual y redirige al usuario a la página de login.
     * </p>
     * 
     * @return Nombre de la plantilla HTML para la página de login.
     */
    @GetMapping("/logout")
    public String cerrarSesion() {
        // Invalidamos la sesión al hacer logout
        session.invalidate();
        return "login"; // Redirige a la página de login
    }
}
