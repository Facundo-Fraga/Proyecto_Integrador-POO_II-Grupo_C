package edu.unam.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;

@Controller
public class ProductsManagerController {

    ProductoService service;

    @Autowired
    ProductsManagerController(ProductoService service) {
        this.service = service;
    }

    @GetMapping({"/productsManager"})
    public String index(Model modelo) {
        modelo.addAttribute("productos", service.buscarProductos());
        return "productsManager";
    }

    @GetMapping("/productsManager/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id, Model modelo) {
        Producto producto = service.buscarProductoId(id);
        if(producto instanceof ProductoSingular) {
            service.eliminarProductoSingular((ProductoSingular)producto);
        }
        else if (producto instanceof ProductoPaquete) {
            service.eliminarPaquete((ProductoPaquete)producto);
        }
        return "redirect:/productsManager";
    }

    

    @GetMapping("/productDetails/{id}")
    public String cargarDetalles(@PathVariable("id") Long id, Model modelo) {
        modelo.addAttribute("producto", service.buscarProductoId(id));
        return "productDetails";
    }
}
