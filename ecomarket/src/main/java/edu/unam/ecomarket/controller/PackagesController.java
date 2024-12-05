package edu.unam.ecomarket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;

@Controller
public class PackagesController {

    private ProductoService service;

    @Autowired
    public PackagesController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/packageCreator")
    public String mostrarFormulario(Model model) {
        model.addAttribute("productosSingulares", service.buscarProductosSingulares());
        model.addAttribute("productoPaquete", new ProductoPaquete());
        return "packageCreator";
    }

    @PostMapping("/packageCreator/crear")
    public String crearPaquete(@ModelAttribute ProductoPaquete productoPaquete,
            @RequestParam List<Long> productosSeleccionados) {

        for (Long id : productosSeleccionados) {
            System.out.println(id);
            ProductoSingular producto = service.buscarProductoSingularPorId(id);
            producto.agregarPaqueteContenedor(productoPaquete);
        }

        service.cargarProducto(productoPaquete);
        return "redirect:/productsManager";
    }

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

    @PostMapping("/packageEditor/{idPaquete}/editar")
    public String guardarCambios(@PathVariable Long idPaquete,
            @ModelAttribute ProductoPaquete productoPaquete,
            @RequestParam(name = "productosSeleccionados", required = false) @Nullable List<Long> idsProductos) {

        List<ProductoSingular> productos = new ArrayList<>(productoPaquete.getProductos());

        for (ProductoSingular producto : productos) {
            producto.eliminarPaqueteContenedor(productoPaquete);
            service.cargarProducto(producto);
        }
        productos.clear();

        if (idsProductos != null) {

            for (Long id : idsProductos) {
                ProductoSingular encontrado = service.buscarProductoSingularPorId(id);
                productos.add(encontrado);
            }
            productoPaquete.setProductos(productos);
        }

        service.actualizarPaquete(idPaquete, productoPaquete);
        return "redirect:/productsManager";
    }
}
