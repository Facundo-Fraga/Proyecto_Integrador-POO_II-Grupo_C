package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddPackageController {

    public AddPackageController() {

    }

    @RequestMapping("/addPackage")
    public String agregarPaquete() {
        return "addPackage";
    }
}
