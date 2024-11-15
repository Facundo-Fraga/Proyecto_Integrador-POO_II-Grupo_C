package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    public LoginController() {

    }

    @RequestMapping(value = {"/", "/login"})
    public String inicio() {
        return "login";
    }
}
