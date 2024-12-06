package edu.unam.ecomarket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los paquetes de productos.
 * 
 * <p>
 * Este controlador permite la creación, edición y gestión de paquetes de productos,
 * incluyendo la asociación de productos individuales a paquetes.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class PackagesController {

    /**
     * Servicio para gestionar las operaciones relacionadas con los productos.
     */
    private ProductoService service;

    /**
     * Constructor con inyección de dependencias para el servicio de productos.
     * 
     * @param service Servicio de productos inyectado.
     */
    @Autowired
    public PackagesController(ProductoService service) {
        this.service = service;
    }

    /**
     * Muestra el formulario para la creación de un paquete de productos.
     * 
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para el formulario de creación de paquetes.
     */
    @GetMapping("/packageCreator")
    public String mostrarFormulario(Model model) {
        model.addAttribute("productosSingulares", service.buscarProductosSingulares());
        model.addAttribute("productoPaquete", new ProductoPaquete());
        return "packageCreator";
    }

    /**
     * Crea un nuevo paquete de productos asociando productos seleccionados al paquete.
     * 
     * @param productoPaquete Objeto que representa el paquete a crear.
     * @param productosSeleccionados Lista de IDs de los productos seleccionados para el paquete.
     * @return Redirección a la página de gestión de productos.
     */
    @PostMapping("/packageCreator/crear")
    public String crearPaquete(@ModelAttribute ProductoPaquete productoPaquete,
                               @RequestParam List<Long> productosSeleccionados) {
        for (Long id : productosSeleccionados) {
            ProductoSingular producto = service.buscarProductoSingularPorId(id);
            producto.agregarPaqueteContenedor(productoPaquete);
        }

        service.cargarProducto(productoPaquete);
        return "redirect:/productsManager";
    }

    /**
     * Muestra el formulario para editar un paquete existente.
     * 
     * @param idPaquete ID del paquete a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para el editor de paquetes.
     * @throws IllegalArgumentException Si el ID del paquete no es válido o no se encuentra.
     */
    @GetMapping("/packageEditor/{idPaquete}/editar")
    public String editarPaquete(@PathVariable Long idPaquete, Model model) {
        try {
            ProductoPaquete paquete = service.buscarPaquetePorId(idPaquete);
            if (paquete == null) {
                throw new IllegalArgumentException("Producto no encontrado");
            }
            model.addAttribute("productoPaquete", paquete);
            model.addAttribute("productosSingulares", service.buscarProductosSingulares());
            return "packageEditor";
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + idPaquete, e);
        }
    }

    /**
     * Guarda los cambios realizados a un paquete existente, incluyendo la asociación de nuevos productos.
     * 
     * @param idPaquete ID del paquete a actualizar.
     * @param productoPaquete Objeto que representa el paquete actualizado.
     * @param idsProductos Lista de IDs de los productos seleccionados para asociar al paquete (opcional).
     * @return Redirección a la página de gestión de productos.
     */
    @PostMapping("/packageEditor/{idPaquete}/editar")
    public String guardarCambios(@PathVariable Long idPaquete,
                                 @ModelAttribute ProductoPaquete productoPaquete,
                                 @RequestParam(name = "productosSeleccionados", required = false) @Nullable List<Long> idsProductos) {

        List<ProductoSingular> productos = new ArrayList<>(productoPaquete.getProductos());

        // Eliminar asociación con productos actuales
        for (ProductoSingular producto : productos) {
            producto.eliminarPaqueteContenedor(productoPaquete);
            service.cargarProducto(producto);
        }
        productos.clear();

        // Asociar nuevos productos si se proporcionaron
        if (idsProductos != null) {
            for (Long id : idsProductos) {
                ProductoSingular encontrado = service.buscarProductoSingularPorId(id);
                productos.add(encontrado);
            }
            productoPaquete.setProductos(productos);
        }

        // Actualizar el paquete
        service.actualizarPaquete(idPaquete, productoPaquete);
        return "redirect:/productsManager";
    }
}
