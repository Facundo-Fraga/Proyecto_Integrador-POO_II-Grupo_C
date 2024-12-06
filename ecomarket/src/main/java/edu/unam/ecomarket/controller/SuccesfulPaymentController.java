package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de manejar la vista para pagos exitosos.
 * 
 * <p>
 * Este controlador redirige a la vista correspondiente cuando un pago ha sido aprobado.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class SuccesfulPaymentController {

    /**
     * Constructor predeterminado del controlador.
     */
    public SuccesfulPaymentController() {
    }

    /**
     * Maneja la solicitud para la página de confirmación de pago exitoso.
     * 
     * @return Nombre de la plantilla HTML para la vista de pago exitoso.
     */
    @RequestMapping("/succesfulPayment")
    public String pagoAprobado() {
        return "succesfulPayment";
    }
}
