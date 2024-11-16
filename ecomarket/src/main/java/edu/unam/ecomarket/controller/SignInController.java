package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {
    
    public SignInController() { 

    }

    @RequestMapping("/signIn")
    public String Registrarse() {
        return "signIn";
    }
}
