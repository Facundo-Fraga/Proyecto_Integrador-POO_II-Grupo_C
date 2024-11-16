package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {

    public ShoppingCartController() { 

    }

    @RequestMapping("/shoppingCart")
    public String verCarrito() {
        return "shoppingCart";
    }
}
