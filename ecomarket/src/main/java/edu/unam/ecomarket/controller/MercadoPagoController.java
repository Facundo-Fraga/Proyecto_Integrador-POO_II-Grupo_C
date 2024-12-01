package edu.unam.ecomarket.controller;

import edu.unam.ecomarket.modelo.payment.BacksUrlDTO;
import edu.unam.ecomarket.modelo.payment.MpNotifyDTO;
import edu.unam.ecomarket.services.MercadoPagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/mercadopago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/preference")
    public String getIdPreference(RedirectAttributes redirectAttributes) {
        // Crear la URL de los Back URLs
        BacksUrlDTO backsUrl = new BacksUrlDTO();
        backsUrl.setSuccess("http://localhost:4567/response/success");
        backsUrl.setPending("http://localhost:4567/response/pending");
        backsUrl.setFailure("http://localhost:4567/response/failed");

        String preferenceUrl;
        try {
            // Llamada al servicio para crear la preferencia de pago
            preferenceUrl = this.mercadoPagoService.createPreference(backsUrl, 
                "http://localhost:4567/api/v1/mercadopago/notify");

            // Redirigir a la URL de la preferencia
            return "redirect:" + preferenceUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear la preferencia de pago: " + e.getMessage();
        }
    }
    
    // Endpoint para recibir la notificación del pago
    @PostMapping(value = "notify")
    public void notifyPay(@RequestBody MpNotifyDTO mpNotify) {
        // Log de la notificación de pago
        System.out.println("Notificación de pago recibida: " + mpNotify.toString());
        
        // Aquí puedes manejar la notificación del pago (actualizar estado, etc.)
    }
}

