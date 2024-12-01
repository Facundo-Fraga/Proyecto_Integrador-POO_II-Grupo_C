package edu.unam.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.unam.ecomarket.services.ProductoService;

@Controller
public class ClientMenuController {
    @Autowired
    ProductoService productoService;

    public ClientMenuController() {

    }

    @GetMapping("/clientMenu")
    public String cargarMenuCliente(Model modelo) {
        modelo.addAttribute("productos", productoService.buscarProductos());
        return "clientMenu";
    }
}
