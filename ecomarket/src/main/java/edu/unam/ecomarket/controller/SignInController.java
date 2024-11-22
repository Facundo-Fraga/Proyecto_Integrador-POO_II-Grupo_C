package edu.unam.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.services.UsuarioService;
import jakarta.validation.Valid;

@Controller
public class SignInController {

    @Autowired
    private UsuarioService servicio;

    public SignInController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/signIn")
    public String index(Model modelo) {
        // Inicializa un nuevo objeto Usuario para el formulario
        modelo.addAttribute("usuario", new Usuario());
        return "signIn";
    }

    @PostMapping("/signIn")
    public String registrarUsuario(@Valid Usuario usuario, BindingResult resultado, Model modelo) {
        if (resultado.hasErrors()) {
            // Si hay errores, devuelve al formulario
            return "signIn";
        }

        // Guardar el usuario usando el servicio
        servicio.cargarUsuario(usuario);

        // Redirige al menú de cliente tras registrarse exitosamente
        return "redirect:/clientMenu";
    }
}
