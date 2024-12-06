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

/**
 * Controlador encargado de gestionar las operaciones relacionadas con la administración de productos.
 * 
 * <p>
 * Este controlador permite listar, eliminar y cargar los detalles de productos
 * individuales o paquetes de productos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class ProductsManagerController {

    /**
     * Servicio para gestionar las operaciones relacionadas con productos.
     */
    private final ProductoService service;

    /**
     * Constructor con inyección de dependencias para el servicio de productos.
     * 
     * @param service Servicio de productos inyectado.
     */
    @Autowired
    ProductsManagerController(ProductoService service) {
        this.service = service;
    }

    /**
     * Muestra la página principal de administración de productos.
     * 
     * @param modelo Modelo para pasar la lista de productos a la vista.
     * @return Nombre de la plantilla HTML para la página de gestión de productos.
     */
    @GetMapping({ "/productsManager" })
    public String index(Model modelo) {
        modelo.addAttribute("productos", service.buscarProductos());
        return "productsManager";
    }

    /**
     * Elimina un producto o paquete de productos basado en su ID.
     * 
     * <p>
     * Si el producto es de tipo singular, utiliza el método correspondiente para eliminarlo.
     * Si el producto es un paquete, utiliza el método para eliminar paquetes.
     * </p>
     * 
     * @param id     ID del producto a eliminar.
     * @param modelo Modelo para pasar información a la vista (opcional).
     * @return Redirección a la página principal de gestión de productos.
     */
    @GetMapping("/productsManager/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id, Model modelo) {
        Producto producto = service.buscarProductoId(id);
        if (producto instanceof ProductoSingular) {
            service.eliminarProductoSingular((ProductoSingular) producto);
        } else if (producto instanceof ProductoPaquete) {
            service.eliminarPaquete((ProductoPaquete) producto);
        }
        return "redirect:/productsManager";
    }

    /**
     * Carga los detalles de un producto individual basado en su ID.
     * 
     * @param id     ID del producto a mostrar.
     * @param modelo Modelo para pasar los detalles del producto a la vista.
     * @return Nombre de la plantilla HTML para mostrar los detalles del producto.
     */
    @GetMapping("/productDetails/{id}")
    public String cargarDetallesProducto(@PathVariable("id") Long id, Model modelo) {
        modelo.addAttribute("producto", service.buscarProductoId(id));
        return "productDetails";
    }

    /**
     * Carga los detalles de un paquete de productos basado en su ID.
     * 
     * @param id     ID del paquete a mostrar.
     * @param modelo Modelo para pasar los detalles del paquete a la vista.
     * @return Nombre de la plantilla HTML para mostrar los detalles del paquete.
     */
    @GetMapping("/packageDetails/{id}")
    public String cargarDetallesPaquete(@PathVariable("id") Long id, Model modelo) {
        modelo.addAttribute("productoPaquete", service.buscarProductoId(id));
        return "packageDetails";
    }
}
