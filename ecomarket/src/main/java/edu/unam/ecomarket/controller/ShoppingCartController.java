package edu.unam.ecomarket.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.CarritoService;

/**
 * Controlador para gestionar las operaciones relacionadas con el carrito de compras.
 * 
 * <p>
 * Este controlador permite ver los productos en el carrito, añadir productos,
 * eliminar productos y actualizar cantidades.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class ShoppingCartController {

    /**
     * Servicio para gestionar las operaciones del carrito de compras.
     */
    private final CarritoService carritoService;

    /**
     * Constructor que inicializa el servicio del carrito de compras.
     * 
     * @param carritoService Servicio del carrito de compras inyectado.
     */
    public ShoppingCartController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    /**
     * Muestra los productos actualmente en el carrito de compras.
     * 
     * @param model Modelo para pasar los datos a la vista.
     * @return Nombre de la plantilla HTML para la vista del carrito de compras.
     */
    @GetMapping("/shoppingCart")
    public String verCarrito(Model model) {
        Map<Producto, Integer> productosEnCarrito = carritoService.obtenerProductosEnCarrito();
        model.addAttribute("productos", productosEnCarrito); // Pasar productos al modelo
        return "shoppingCart";
    }

    /**
     * Elimina un producto del carrito de compras.
     * 
     * @param idProducto ID del producto a eliminar.
     * @param model      Modelo para pasar información a la vista.
     * @return Redirección a la vista del carrito de compras.
     */
    @PostMapping("/remove")
    public String eliminarDelCarrito(@RequestParam Long idProducto, Model model) {
        System.out.println("Eliminando producto con ID: " + idProducto); // Log de depuración
        carritoService.eliminarProducto(idProducto);
        return "redirect:/shoppingCart"; // Redirigir para actualizar el carrito
    }

    /**
     * Añade un producto al carrito de compras.
     * 
     * @param idProducto ID del producto a añadir.
     * @param model      Modelo para pasar información a la vista.
     * @return Redirección a la vista del menú del cliente.
     */
    @PostMapping("/add")
    public String añadirAlCarrito(@RequestParam Long idProducto, Model model) {
        carritoService.añadirProducto(idProducto);
        return "redirect:/clientMenu"; // Redirige al menú del cliente
    }

    /**
     * Actualiza la cantidad de un producto en el carrito de compras.
     * 
     * @param idProducto ID del producto cuya cantidad se debe actualizar.
     * @param cantidad   Nueva cantidad para el producto.
     * @return Redirección a la vista del carrito de compras.
     */
    @PostMapping("/update-cantidad")
    public String actualizarCantidad(
            @RequestParam("idProducto") Long idProducto,
            @RequestParam("cantidad") int cantidad) {
        carritoService.actualizarCantidad(idProducto, cantidad);
        return "redirect:/shoppingCart"; // Redirige al carrito después de actualizar
    }
}
