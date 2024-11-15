package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductDetailsController {

    public ProductDetailsController() {

    }

    @RequestMapping("/productDetails")
    public String detallesProducto() {
        return "productDetails";
    }
}
