package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class paymentController {

    public paymentController() {

    }

    @RequestMapping("/payment")
    public String pagar() {
        return "payment";
    }
}
