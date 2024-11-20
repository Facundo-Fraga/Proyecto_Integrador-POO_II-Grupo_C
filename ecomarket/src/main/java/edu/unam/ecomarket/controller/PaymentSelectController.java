package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentSelectController {
    
    public PaymentSelectController() {

    }

    @RequestMapping("/payment")
    public String metodoPago() {
        return "payment";
    }
}