package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de manejar las operaciones relacionadas con el menú principal.
 * 
 * <p>
 * Este controlador se encarga de redirigir a la vista del menú principal de la aplicación.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class MainMenuController {

    /**
     * Constructor predeterminado del controlador del menú principal.
     * Actualmente no realiza ninguna inicialización adicional, pero se incluye
     * para posibles extensiones futuras.
     */
    public MainMenuController() {

    }

    /**
     * Maneja las solicitudes para la vista del menú principal.
     * 
     * <p>
     * Este método redirige a la plantilla HTML del menú principal.
     * </p>
     * 
     * @return Nombre de la plantilla HTML para la vista del menú principal.
     */
    @RequestMapping("/mainMenu")
    public String menuPrincipal() {
        return "mainMenu";
    }
}
