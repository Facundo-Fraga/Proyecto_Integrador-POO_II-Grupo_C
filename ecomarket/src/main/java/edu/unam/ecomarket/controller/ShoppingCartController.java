package edu.unam.ecomarket.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.CarritoService;

@Controller
public class ShoppingCartController {

    private final CarritoService carritoService;

    public ShoppingCartController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Mostrar los productos en el carrito
    @GetMapping("/shoppingCart")
    public String verCarrito(Model model) {
        Map<Producto, Integer> productosEnCarrito = carritoService.obtenerProductosEnCarrito();
        model.addAttribute("productos", productosEnCarrito); // Cambié el nombre de "producto" a "productos"
        return "shoppingCart"; // Nombre de la vista
    }

    @PostMapping("/remove")
    public String eliminarDelCarrito(@RequestParam Long idProducto, Model model) {
        System.out.println("Eliminando producto con ID: " + idProducto); // Log de depuración
        carritoService.eliminarProducto(idProducto);
        return "redirect:/shoppingCart"; // Redirigir para actualizar el carrito
    }

    @PostMapping("/add")
    public String añadirAlCarrito(@RequestParam Long idProducto, Model model) {
        carritoService.añadirProducto(idProducto);
        // Redirige a la vista del carrito después de agregar el producto
        return "redirect:/clientMenu";
    }

    
    
    @PostMapping("/update-cantidad")
    public String actualizarCantidad(
            @RequestParam("idProducto") Long idProducto,
            @RequestParam("cantidad") int cantidad) {
        carritoService.actualizarCantidad(idProducto, cantidad);
        return "redirect:/shoppingCart"; // Redirige de nuevo al carrito después de actualizar
    }

}
