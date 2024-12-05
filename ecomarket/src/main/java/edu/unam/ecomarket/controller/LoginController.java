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

@Controller
@NoArgsConstructor
public class LoginController {

    private UsuarioService servicio;

    @Autowired
    private HttpSession session;
    @Autowired
    private CarritoService carritoService;

    @Autowired
    public LoginController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping({ "/", "/login" })
    public String index() {
        return "login"; // Redirige a la página de login
    }

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
            // Si es otro tipo de usuario, redirige al menú principal (por ejemplo,
            // administrador)
            return "redirect:/mainMenu";
        }

        // Si las credenciales son incorrectas, mostramos el error
        modelo.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
        return "login";
    }

    @GetMapping("/logout")
    public String cerrarSesion() {
        // Invalidamos la sesión al hacer logout
        session.invalidate();
        return "login"; // Redirige a la página de login
    }
}