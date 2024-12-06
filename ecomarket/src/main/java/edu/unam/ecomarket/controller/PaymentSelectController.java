package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import edu.unam.ecomarket.modelo.EnvioLocal;
import edu.unam.ecomarket.modelo.EnvioNacional;
import edu.unam.ecomarket.modelo.EnvioProvincial;
import edu.unam.ecomarket.modelo.MetodoEnvio;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador para gestionar la selección de métodos de pago y envíos.
 * 
 * <p>
 * Este controlador permite seleccionar el tipo de envío para una compra,
 * calcula el costo del envío y guarda la información en la sesión para usarla
 * en pasos posteriores del proceso de pago.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class PaymentSelectController {

    /**
     * Objeto para gestionar la sesión actual.
     */
    @Autowired
    private HttpSession session;

    /**
     * Constructor predeterminado del controlador.
     */
    public PaymentSelectController() {
    }

    /**
     * Muestra la página para la selección de métodos de pago.
     * 
     * @return Nombre de la plantilla HTML para la vista de métodos de pago.
     */
    @RequestMapping("/payment")
    public String metodoPago() {
        return "payment";
    }

    /**
     * Procesa la selección del tipo de envío y calcula el costo correspondiente.
     * 
     * <p>
     * Dependiendo del tipo de envío seleccionado (local, provincial o nacional),
     * se crea una instancia del envío correspondiente, se calcula su costo y se
     * guarda en la sesión.
     * </p>
     * 
     * @param tipoEnvio Tipo de envío seleccionado por el usuario (local, provincial o nacional).
     * @param model     Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para continuar con el proceso de pago.
     * @throws IllegalArgumentException Si el tipo de envío proporcionado no es válido.
     */
    @PostMapping("/payment")
    public String iniciarCompra(@RequestParam String tipoEnvio, Model model) {
        MetodoEnvio envio;

        switch (tipoEnvio) {
            case "local":
                envio = new EnvioLocal();
                break;
            case "provincial":
                envio = new EnvioProvincial();
                break;
            case "nacional":
                envio = new EnvioNacional();
                break;
            default:
                throw new IllegalArgumentException("Tipo de envío no válido");
        }

        // Calcular el costo del envío
        envio.calcularCosto();

        // Guardar el costo del envío en la sesión para usarlo en el siguiente paso
        session.setAttribute("envio", envio);
        System.out.println("Envio guardado en sesión: " + envio);

        // Pasar el objeto envío al modelo para mostrarlo en la vista
        model.addAttribute("envio", envio);
        return "payment"; // Redirige a la vista de selección de método de pago
    }
}
