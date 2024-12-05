package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainMenuController {

    public MainMenuController() {

    }

    @RequestMapping("/mainMenu")
    public String menuPrincipal() {
        return "mainMenu";
    }

    

}
