package edu.unam.ecomarket.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;
import jakarta.validation.Valid;

@Controller
public class PruebaController {

    ProductoService service;

    public PruebaController(ProductoService service) {
        this.service = service;
    }

    @GetMapping({"/Prueba"})
    public String index(Model modelo) {;
        return "Prueba";
    }

    @PostMapping("/Prueba")
    public String persistirProducto() {
        
        ProductoSingular producto = new ProductoSingular();
        producto.setNombre("Empanada");
        producto.setPrecio(200.0);
        Map<String, String> detalles = new HashMap<>();
        detalles.put("Categoria", "Comida");
        producto.setDetalles(detalles);

        service.cargarProducto(producto);

        return "Prueba";
    }
}
