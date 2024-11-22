package edu.unam.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import edu.unam.ecomarket.services.ProductoIndividualService;

@Controller
public class ManageProductsController {

    @Autowired
    ProductoIndividualService productoIndividualService;

    public ManageProductsController() { 

    }

    @RequestMapping("/manageProducts")
    public String gestionarProductos() {
        return "manageProducts";
    }

    @GetMapping("/manageProducts")
    public String cargarProductos(Model modelo){
         
        return "";
    }
}
