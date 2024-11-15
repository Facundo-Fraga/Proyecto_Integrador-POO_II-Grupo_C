package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EditPackageController {
    
    public EditPackageController() { 

    }

    @RequestMapping("/editPackage")
    public String editarPaquete() {
        return "editPackage";
    }
}
