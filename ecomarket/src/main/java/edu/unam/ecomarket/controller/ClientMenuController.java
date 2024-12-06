package edu.unam.ecomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.unam.ecomarket.services.ProductoService;

/**
 * Controlador para manejar las funcionalidades del menú del cliente en la aplicación.
 * 
 * <p>
 * Esta clase se encarga de gestionar las solicitudes relacionadas con la página
 * del menú del cliente y de interactuar con el servicio de productos para
 * proporcionar los datos necesarios a la vista.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
public class ClientMenuController {

    /**
     * Servicio de productos que proporciona acceso a las operaciones relacionadas
     * con los productos.
     */
    @Autowired
    ProductoService productoService;

    /**
     * Constructor predeterminado para el controlador del menú del cliente.
     * Actualmente no realiza ninguna inicialización adicional, pero se incluye
     * para posibles extensiones futuras.
     */
    public ClientMenuController() {

    }

    /**
     * Maneja la solicitud GET para la página del menú del cliente.
     * 
     * <p>
     * Este método carga la lista de productos desde el servicio de productos y la
     * agrega al modelo para que esté disponible en la vista. Luego, retorna el
     * nombre de la plantilla HTML correspondiente.
     * </p>
     * 
     * @param modelo El modelo que se utiliza para pasar datos a la vista.
     * @return El nombre de la plantilla HTML para la página del menú del cliente.
     */
    @GetMapping("/clientMenu")
    public String cargarMenuCliente(Model modelo) {
        modelo.addAttribute("productos", productoService.buscarProductos());
        return "clientMenu";
    }
}
