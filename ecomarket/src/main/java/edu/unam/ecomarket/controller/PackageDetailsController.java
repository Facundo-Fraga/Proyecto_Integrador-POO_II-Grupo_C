package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PackageDetailsController {

    public PackageDetailsController() {

    }

    @RequestMapping("/packageDetails")
    public String verDetallePaquete() {
        return "packageDetails";
    }
}
