package edu.unam.ecomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;
import jakarta.validation.Valid;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los productos singulares.
 * 
 * <p>
 * Este controlador permite crear, editar y gestionar productos singulares
 * en el sistema.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class SingleProductController {

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
    public SingleProductController(ProductoService service) {
        this.service = service;
    }

    /**
     * Muestra el formulario para crear un nuevo producto singular.
     * 
     * @param modelo Modelo para pasar los datos a la vista.
     * @return Nombre de la plantilla HTML para el formulario de creación de productos singulares.
     */
    @GetMapping("/singleProductCreator")
    public String index(Model modelo) {
        modelo.addAttribute("productoSingular", new ProductoSingular());
        return "singleProductCreator";
    }

    /**
     * Procesa la creación de un nuevo producto singular.
     * 
     * <p>
     * Valida los datos ingresados, permite agregar detalles adicionales mediante
     * listas de claves y valores, y guarda el producto en el sistema.
     * </p>
     * 
     * @param productoSingular Objeto que representa el producto singular a crear.
     * @param resultado        Resultado de las validaciones del formulario.
     * @param modelo           Modelo para pasar datos a la vista.
     * @param claves           Lista opcional de claves para los detalles del producto.
     * @param valores          Lista opcional de valores para los detalles del producto.
     * @return Redirección a la vista de gestión de productos si la creación es exitosa,
     *         o vuelve al formulario en caso de errores.
     */
    @PostMapping("/singleProductCreator/crear")
    public String agregarProducto(@Valid ProductoSingular productoSingular, BindingResult resultado, Model modelo,
                                  @RequestParam(value = "detalles_clave[]", required = false) List<String> claves,
                                  @RequestParam(value = "detalles_valor[]", required = false) List<String> valores) {
        if (resultado.hasErrors()) {
            return "singleProductCreator";
        }
        service.crearProductoSingular(productoSingular, claves, valores);
        return "redirect:/productsManager";
    }

    /**
     * Muestra el formulario para editar un producto singular existente.
     * 
     * <p>
     * Busca el producto por su ID y lo pasa al modelo para prellenar el formulario.
     * </p>
     * 
     * @param id    ID del producto a editar.
     * @param model Modelo para pasar los datos a la vista.
     * @return Nombre de la plantilla HTML para el formulario de edición de productos singulares.
     * @throws IllegalArgumentException Si el ID no es válido o el producto no se encuentra.
     */
    @GetMapping("/singleProductEditor/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        try {
            Long idProducto = Long.parseLong(id);
            Producto producto = service.buscarProductoId(idProducto);
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado");
            }
            model.addAttribute("productoSingular", producto);
            return "singleProductEditor";
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID proporcionado no es válido: " + id, e);
        }
    }

    /**
     * Procesa la edición de un producto singular existente.
     * 
     * <p>
     * Valida los datos ingresados, actualiza los detalles adicionales mediante
     * listas de claves y valores, y guarda los cambios en el sistema.
     * </p>
     * 
     * @param id               ID del producto a editar.
     * @param productoSingular Objeto que representa el producto singular con los nuevos datos.
     * @param resultado        Resultado de las validaciones del formulario.
     * @param claves           Lista opcional de claves para los detalles del producto.
     * @param valores          Lista opcional de valores para los detalles del producto.
     * @return Redirección a la vista de gestión de productos si la edición es exitosa,
     *         o vuelve al formulario en caso de errores.
     */
    @PostMapping("/singleProductEditor/{id}/editar")
    public String editarProducto(@PathVariable Long id, @Valid ProductoSingular productoSingular,
                                 BindingResult resultado,
                                 @RequestParam(value = "detalles_clave[]", required = false) List<String> claves,
                                 @RequestParam(value = "detalles_valor[]", required = false) List<String> valores) {
        if (resultado.hasErrors()) {
            return "singleProductEditor";
        }
        service.actualizarProductoSingular(id, productoSingular, claves, valores);
        return "redirect:/productsManager";
    }
}
