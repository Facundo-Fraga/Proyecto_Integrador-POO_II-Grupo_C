package edu.unam.ecomarket.controller;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.CarritoService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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

    // Actualizar la cantidad de un producto en el carrito
    @PostMapping("/updateQuantity")
    public String actualizarCantidad(@RequestParam Long idProducto, @RequestParam int cantidad) {
        carritoService.actualizarCantidad(idProducto, cantidad);
        return "redirect:/shoppingCart";  // Redirigir para actualizar el carrito
    }

}
