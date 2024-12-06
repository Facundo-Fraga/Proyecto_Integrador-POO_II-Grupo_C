package edu.unam.ecomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de gestionar las vistas de detalles de productos y paquetes.
 * 
 * <p>
 * Este controlador proporciona los endpoints para acceder a las páginas que muestran
 * detalles específicos de productos individuales y paquetes de productos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class ProductDetailsController {

    /**
     * Constructor predeterminado del controlador.
     */
    public ProductDetailsController() {
    }

    /**
     * Maneja las solicitudes para la vista de detalles de un producto.
     * 
     * @return Nombre de la plantilla HTML para la página de detalles del producto.
     */
    @RequestMapping("/productDetails")
    public String detallesProducto() {
        return "productDetails";
    }

    /**
     * Maneja las solicitudes para la vista de detalles de un paquete de productos.
     * 
     * @return Nombre de la plantilla HTML para la página de detalles del paquete.
     */
    @RequestMapping("/packageDetails")
    public String detallesPaquete() {
        return "packageDetails";
    }
}
