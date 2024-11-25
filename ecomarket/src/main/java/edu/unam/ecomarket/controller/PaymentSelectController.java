package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import edu.unam.ecomarket.services.MercadoPagoService;
@Controller
public class PaymentSelectController {
    
    @Autowired
    MercadoPagoService mercadoPagoService;
    public PaymentSelectController() {

    }

    @RequestMapping("/payment")
    public String metodoPago() {
        return "payment";
    }

}