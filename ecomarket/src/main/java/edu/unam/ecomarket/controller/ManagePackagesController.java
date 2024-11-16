package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagePackagesController {

    public ManagePackagesController() {

    }

    @RequestMapping("/managePackages")
    public String gestionarPaquetes() {
        return "managePackages";
    }
}
