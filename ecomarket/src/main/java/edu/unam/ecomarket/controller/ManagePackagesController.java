package edu.unam.ecomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.unam.ecomarket.modelo.ProductoIndividual;

import org.springframework.ui.Model;

import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.services.ProductoPaqueteService;
@Controller
public class ManagePackagesController {
    
    @Autowired
    ProductoPaqueteService productoPaqueteService;
    public ManagePackagesController() {

    }

    @RequestMapping("/managePackages")
    public String gestionarPaquetes() {
        return "managePackages";
    }
    @GetMapping("/managePackages")
    public String cargarPaquetes(Model modelo){
        List<ProductoPaquete> paquetes = productoPaqueteService.obtenerTodos();
        modelo.addAttribute("paquetes", paquetes);
        return "managePackages";
    }
    
}
