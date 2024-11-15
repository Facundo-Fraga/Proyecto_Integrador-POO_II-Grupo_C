package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddProductController {

    public AddProductController() {

    }

    @RequestMapping("/addProduct")
    public String agregarProducto() {
        return "addProduct";
    }
}
