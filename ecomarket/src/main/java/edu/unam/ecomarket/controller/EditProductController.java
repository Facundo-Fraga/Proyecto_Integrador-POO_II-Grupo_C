package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EditProductController {

    public EditProductController() {

    }

    @RequestMapping("/editProduct")
    public String editarProducto() {
        return "editProduct";
    }
}
