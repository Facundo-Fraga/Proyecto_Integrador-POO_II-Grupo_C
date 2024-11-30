package edu.unam.ecomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;

@Controller
public class PackagesController {

    private ProductoService service;

    @Autowired
    public PackagesController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/packageCreator")
    public String mostrarFormulario(Model model) {
        model.addAttribute("productosSingulares", service.buscarProductosSingulares());
        model.addAttribute("productoPaquete", new ProductoPaquete());
        return "packageCreator";
    }

    @PostMapping("/packageCreator/crear")
    public String crearPaquete(@ModelAttribute ProductoPaquete productoPaquete, @RequestParam List<Long> productosSeleccionados) {

        for (Long id : productosSeleccionados) {
            System.out.println(id);
            ProductoSingular producto = service.buscarProductoSingularPorId(id);
            producto.agregarPaqueteContenedor(productoPaquete);
        }

        service.cargarProducto(productoPaquete);
        return "redirect:/productsManager";
    }
}
