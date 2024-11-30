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

@Controller
public class SingleProductController {

    private final ProductoService service;

    @Autowired
    public SingleProductController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/singleProductCreator")
    public String index(Model modelo) {
        modelo.addAttribute("productoSingular", new ProductoSingular());
        return "singleProductCreator";
    }

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

    @GetMapping("/singleProductEditor/{id}")
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

