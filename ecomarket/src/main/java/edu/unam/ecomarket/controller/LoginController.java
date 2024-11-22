package edu.unam.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.Usuario;
import edu.unam.ecomarket.services.UsuarioService;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class LoginController {

    private UsuarioService servicio;

    @Autowired
    public LoginController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping(value = {"/", "/login"})
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam("nombre") String nombre,
            @RequestParam("contrasenia") String contrasenia,
            Model modelo) {
        
        Usuario encontrado = servicio.buscarUsuarioPorNombre(nombre);

        if (encontrado != null && contrasenia.equals(encontrado.getContrasenia())) {
            return "redirect:/clientMenu";
        }

        modelo.addAttribute("error", "Nombre de usuario o contraseña incorrectos.");
        return "login";
    }
}
