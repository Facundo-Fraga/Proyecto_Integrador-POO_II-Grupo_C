package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccesfulPaymentController {
    
    public SuccesfulPaymentController() {

    }

    @RequestMapping("/succesfulPayment")
    public String pagoAprobado() {
        return "succesfulPayment";
    }
}
