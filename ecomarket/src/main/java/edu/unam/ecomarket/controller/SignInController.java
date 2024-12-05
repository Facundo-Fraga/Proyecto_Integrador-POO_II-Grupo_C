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

@Controller
public class SignInController {

    @Autowired
    private UsuarioService servicio;
    @Autowired
    private HttpSession session;
    public SignInController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/signIn")
    public String index(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "signIn";
    }

    @PostMapping("/signIn")
    public String registrarUsuario(@Valid Cliente cliente, BindingResult resultado, Model modelo) {
        if (resultado.hasErrors()) {
            return "signIn";
        }

        if(servicio.buscarUsuarioPorNombre(cliente.getNombre()) == null){
            if (cliente.getContrasenia().length() < 8) {
                modelo.addAttribute("error", "La contraseña debe tener al menos 8 caracteres");
                return "signIn";
            }
            cliente.setContrasenia(BCrypt.hashpw(cliente.getContrasenia(), BCrypt.gensalt()));
            servicio.cargarUsuario(cliente);
            return "redirect:/login";
        }

        modelo.addAttribute("error", "Ya existe una cuenta con ese Nombre de Usuario.");
        return "signIn";
    }
}
