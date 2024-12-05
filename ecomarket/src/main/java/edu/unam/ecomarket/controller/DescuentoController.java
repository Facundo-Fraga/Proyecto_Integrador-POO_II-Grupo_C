package edu.unam.ecomarket.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.unam.ecomarket.modelo.Descuento;
import edu.unam.ecomarket.modelo.DescuentoFijo;
import edu.unam.ecomarket.modelo.DescuentoPorcentaje;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.DescuentosService;
import edu.unam.ecomarket.services.ProductoService;

@Controller
public class DescuentoController {

    @Autowired
    private DescuentosService descuentoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/manageDiscounts")
    public String index(Model modelo) {
        modelo.addAttribute("descuentos", descuentoService.obtenerDescuentos());
        return "manageDiscounts";
    }

    @GetMapping("manageDiscounts/eliminar/{id}")
    public String eliminarDescuento(@PathVariable("id") Long id, Model modelo) {
        descuentoService.eliminarDescuento(id);
        return "redirect:/manageDiscounts";
    }

    @GetMapping("/discountsCreator")
    public String cargarFormularioCreacion(Model modelo) {
        modelo.addAttribute("descuento", new Descuento());
        modelo.addAttribute("productos", productoService.buscarProductos());

        return "discountsCreator";
    }

    @PostMapping("/discountsCreator/crear")
    public String crearDescuento(@ModelAttribute("descuento") Descuento descuento,
            @RequestParam("estrat") String tipoEstrategia, // Campo oculto con el tipo de estrategia
            @RequestParam(value = "productosSeleccionados", required = false) List<Long> productosSeleccionados,
            @RequestParam(value = "porcentaje", required = false) Double porcentaje,
            @RequestParam(value = "montoFijo", required = false) Double montoFijo,
            BindingResult result, Model model) {

        EstrategiaDescuento estrategia;
        if ("porcentaje".equals(tipoEstrategia)) {
            DescuentoPorcentaje estrategiaPorcentaje = new DescuentoPorcentaje();
            estrategia = estrategiaPorcentaje;
            estrategia.setValorDescuento(porcentaje);
            descuentoService.persistirEstrategias(estrategia);
        } else if ("montoFijo".equals(tipoEstrategia)) {
            DescuentoFijo estrategiaMontoFijo = new DescuentoFijo();
            estrategia = estrategiaMontoFijo;
            estrategia.setValorDescuento(montoFijo);
            descuentoService.persistirEstrategias(estrategia);
        } else {
            result.rejectValue("estrategia", "error.descuento", "Estrategia inválida.");
            return "crear-descuento";
        }

        descuento.setEstrategia(estrategia);

        // Validación de fechas
        LocalDate hoy = LocalDate.now();
        if (descuento.getFechaInicio().isBefore(hoy)) {
            result.rejectValue("fechaInicio", "error.descuento", "La fecha de inicio no puede ser anterior a hoy.");
        }
        if (descuento.getFechaFin().isBefore(descuento.getFechaInicio())) {
            result.rejectValue("fechaFin", "error.descuento",
                    "La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        // Si hay errores, devuelve al formulario
        if (result.hasErrors()) {
            model.addAttribute("productos", productoService.buscarProductos());
            return "discountsCreator";
        }

        // Asociar productos seleccionados
        if (productosSeleccionados != null) {
            List<Producto> productos = productoService.buscarProductos(productosSeleccionados);
            descuento.setProductos(productos);
        } else {
            List<Producto> productos = productoService.buscarProductos();
            descuento.setProductos(productos);
        }

        // Guardar descuento
        descuentoService.guardarDescuento(descuento);
        System.out.println(descuento.getEstrategia());
        return "redirect:/manageDiscounts"; // Redirige a la lista de descuentos
    }
}
