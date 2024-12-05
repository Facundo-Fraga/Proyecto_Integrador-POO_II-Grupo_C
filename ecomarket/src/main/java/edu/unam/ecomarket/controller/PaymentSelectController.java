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
import edu.unam.ecomarket.services.MercadoPagoService;
import jakarta.servlet.http.HttpSession;
@Controller
public class PaymentSelectController {
    
    @Autowired
    private HttpSession session;
    
    public PaymentSelectController() {

    }

    @RequestMapping("/payment")
    public String metodoPago() {
        return "payment";
    }

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

        envio.calcularCosto();

        // Guardar el costo del envío en la sesión para usarlo en el segundo paso
        session.setAttribute("envio", envio);
        System.out.println("Envio guardado en sesión: " + envio);
        model.addAttribute("envio", envio);
        return "payment"; // Redirige a la vista de selección de método de pago
    }
}