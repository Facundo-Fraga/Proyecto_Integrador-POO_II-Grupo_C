package edu.unam.ecomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;
import jakarta.validation.Valid;

@Controller
public class SingleProductController {

    ProductoService service;

    @Autowired
    SingleProductController(ProductoService service) {
        this.service = service;
    }

    @GetMapping({"/singleProductManager"})
    public String index(Model modelo) {
        modelo.addAttribute("productoSingular", new ProductoSingular());
        return "singleProductManager";
    }

    @PostMapping("/singleProductManager/crear")
    public String agregarProducto(@Valid ProductoSingular productoSingular, BindingResult resultado, Model modelo, 
                                    @RequestParam("detalles_clave[]") List<String> claves, 
                                    @RequestParam("detalles_valor[]") List<String> valores) {
        
        if(resultado.hasErrors()) {
            return "singleProductManager";
        }
        for (int i = 0; i < claves.size(); i++) {
            productoSingular.getDetalles().put(claves.get(i), valores.get(i));
        }
        service.cargarProducto(productoSingular);
        return "redirect:/productsManager";
    }
}
