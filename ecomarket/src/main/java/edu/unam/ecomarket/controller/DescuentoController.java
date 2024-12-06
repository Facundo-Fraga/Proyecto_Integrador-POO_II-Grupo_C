package edu.unam.ecomarket.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.unam.ecomarket.modelo.*;
import edu.unam.ecomarket.services.DescuentosService;
import edu.unam.ecomarket.services.ProductoService;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los descuentos.
 * 
 * <p>
 * Permite la creación, visualización, y eliminación de descuentos, además de asociar
 * estrategias de descuento a productos específicos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class DescuentoController {

    /**
     * Servicio para la gestión de descuentos.
     */
    @Autowired
    private DescuentosService descuentoService;

    /**
     * Servicio para la gestión de productos.
     */
    @Autowired
    private ProductoService productoService;

    /**
     * Muestra la página principal de gestión de descuentos.
     * 
     * @param modelo Modelo para pasar los datos necesarios a la vista.
     * @return Nombre de la plantilla HTML correspondiente.
     */
    @GetMapping("/manageDiscounts")
    public String index(Model modelo) {
        modelo.addAttribute("descuentos", descuentoService.obtenerDescuentos());
        return "manageDiscounts";
    }

    /**
     * Elimina un descuento específico basado en su ID.
     * 
     * @param id ID del descuento a eliminar.
     * @param modelo Modelo utilizado para la interacción con la vista.
     * @return Redirección a la página de gestión de descuentos.
     */
    @GetMapping("manageDiscounts/eliminar/{id}")
    public String eliminarDescuento(@PathVariable("id") Long id, Model modelo) {
        descuentoService.eliminarDescuento(id);
        return "redirect:/manageDiscounts";
    }

    /**
     * Carga el formulario para la creación de un nuevo descuento.
     * 
     * @param modelo Modelo para pasar los datos necesarios a la vista.
     * @return Nombre de la plantilla HTML correspondiente.
     */
    @GetMapping("/discountsCreator")
    public String cargarFormularioCreacion(Model modelo) {
        modelo.addAttribute("descuento", new Descuento());
        modelo.addAttribute("productos", productoService.buscarProductos());
        return "discountsCreator";
    }

    /**
     * Procesa la creación de un nuevo descuento con su estrategia y productos asociados.
     * 
     * @param descuento Objeto que representa el descuento a crear.
     * @param tipoEstrategia Tipo de estrategia seleccionada ("porcentaje" o "montoFijo").
     * @param productosSeleccionados Lista de IDs de productos seleccionados (opcional).
     * @param porcentaje Valor del descuento en porcentaje (opcional).
     * @param montoFijo Valor fijo del descuento (opcional).
     * @param result Objeto para manejar errores de validación.
     * @param model Modelo para pasar datos a la vista.
     * @return Redirección a la página de gestión de descuentos o el formulario si hay errores.
     */
    @PostMapping("/discountsCreator/crear")
    public String crearDescuento(
            @ModelAttribute("descuento") Descuento descuento,
            @RequestParam("estrat") String tipoEstrategia,
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

        LocalDate hoy = LocalDate.now();
        if (descuento.getFechaInicio().isBefore(hoy)) {
            result.rejectValue("fechaInicio", "error.descuento", "La fecha de inicio no puede ser anterior a hoy.");
        }
        if (descuento.getFechaFin().isBefore(descuento.getFechaInicio())) {
            result.rejectValue("fechaFin", "error.descuento", "La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        if (result.hasErrors()) {
            model.addAttribute("productos", productoService.buscarProductos());
            return "discountsCreator";
        }

        if (productosSeleccionados != null) {
            List<Producto> productos = productoService.buscarProductos(productosSeleccionados);
            descuento.setProductos(productos);
        } else {
            List<Producto> productos = productoService.buscarProductos();
            descuento.setProductos(productos);
        }

        descuentoService.guardarDescuento(descuento);
        return "redirect:/manageDiscounts";
    }

    //Disculpen la maraña de código :')
}
