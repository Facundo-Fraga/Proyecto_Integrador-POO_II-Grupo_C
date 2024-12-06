package edu.unam.ecomarket.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.Pedido;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.CarritoService;
import edu.unam.ecomarket.services.PedidoService;
import edu.unam.ecomarket.services.UsuarioService;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador para manejar las respuestas de pago de MercadoPago.
 * 
 * <p>
 * Este controlador gestiona las respuestas a los pagos realizados, incluyendo
 * casos de éxito, pagos pendientes y pagos fallidos. Además, se encarga de
 * crear un pedido basado en los productos en el carrito cuando el pago es
 * exitoso.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Controller
@RequestMapping("/response")
public class PaymentResponseController {

    /**
     * Servicio para gestionar el carrito de compras.
     */
    @Autowired
    private CarritoService carritoService;

    /**
     * Servicio para gestionar usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Servicio para gestionar pedidos.
     */
    @Autowired
    private PedidoService pedidoService;

    /**
     * Objeto para gestionar la sesión actual.
     */
    @Autowired
    private HttpSession session;

    /**
     * Logger para registrar eventos y errores.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Maneja las respuestas de pagos exitosos.
     * 
     * <p>
     * Procesa un pago exitoso, crea un pedido basado en el carrito de compras y
     * vacía el carrito.
     * </p>
     * 
     * @param paymentId         ID del pago realizado.
     * @param status            Estado del pago.
     * @param externalReference Referencia externa del pago.
     * @param model             Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para confirmar el éxito del pago o para
     *         manejar errores.
     */
    @GetMapping("/success")
    public String paymentSuccess(@RequestParam(value = "payment_id", required = false) String paymentId,
                                  @RequestParam(value = "status", required = false) String status,
                                  @RequestParam(value = "external_reference", required = false) String externalReference,
                                  Model model) {
        logger.info("Pago exitoso recibido: payment_id={}, status={}, external_reference={}", paymentId, status,
                externalReference);

        model.addAttribute("title", "Pago Exitoso");
        model.addAttribute("message", "¡Gracias por tu compra!");
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("status", status);
        model.addAttribute("externalReference", externalReference);

        // Obtener cliente en sesión
        Cliente cliente = usuarioService.obtenerClienteEnSesion(session);
        if (cliente == null) {
            logger.error("No se encontró cliente en sesión");
            model.addAttribute("error", "Error: no se pudo identificar al cliente.");
            return "errorPageCliente";
        }

        try {
            // Recuperar información del envío desde la sesión
            MetodoEnvio envio = (MetodoEnvio) session.getAttribute("envio");
            if (envio == null) {
                logger.error("No se encontró información de envío en la sesión");
                model.addAttribute("error", "Error: no se pudo encontrar información de envío.");
                return "errorPageCliente";
            }

            // Obtener productos del carrito
            Map<Producto, Integer> productosCarrito = carritoService.obtenerProductosEnCarrito();
            if (productosCarrito.isEmpty()) {
                model.addAttribute("error", "El carrito está vacío.");
                return "errorPageCliente";
            }

            // Crear pedido
            Pedido pedido = pedidoService.crearPedido(cliente, productosCarrito, paymentId, status, envio);
            model.addAttribute("pedido", pedido);
            carritoService.vaciarCarrito(); // Vaciar el carrito después de procesar el pedido
            return "succesfulPayment"; // Vista que muestra el éxito del pedido
        } catch (Exception e) {
            logger.error("Error al procesar la notificación de pago", e);
            model.addAttribute("error", "Ocurrió un error al procesar tu pedido.");
            return "errorPageCliente";
        }
    }

    /**
     * Maneja las respuestas de pagos pendientes.
     * 
     * <p>
     * Muestra un mensaje al usuario indicando que el pago está pendiente.
     * </p>
     * 
     * @param paymentId         ID del pago.
     * @param status            Estado del pago.
     * @param externalReference Referencia externa del pago.
     * @param model             Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para notificar el estado pendiente del
     *         pago.
     */
    @GetMapping("/pending")
    public String paymentPending(@RequestParam(value = "payment_id", required = false) String paymentId,
                                  @RequestParam(value = "status", required = false) String status,
                                  @RequestParam(value = "external_reference", required = false) String externalReference,
                                  Model model) {
        logger.info("Pago pendiente: payment_id={}, status={}, external_reference={}", paymentId, status,
                externalReference);

        model.addAttribute("title", "Pago Pendiente");
        model.addAttribute("message", "Tu pago está pendiente. Por favor, espera la confirmación.");
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("status", status);
        model.addAttribute("externalReference", externalReference);

        return "paymentResponse";
    }

    /**
     * Maneja las respuestas de pagos fallidos.
     * 
     * <p>
     * Muestra un mensaje al usuario indicando que el pago no pudo completarse.
     * </p>
     * 
     * @param paymentId         ID del pago.
     * @param status            Estado del pago.
     * @param externalReference Referencia externa del pago.
     * @param model             Modelo para pasar datos a la vista.
     * @return Nombre de la plantilla HTML para notificar el fallo del pago.
     */
    @GetMapping("/failed")
    public String paymentFailed(@RequestParam(value = "payment_id", required = false) String paymentId,
                                 @RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "external_reference", required = false) String externalReference,
                                 Model model) {
        logger.info("Pago fallido: payment_id={}, status={}, external_reference={}", paymentId, status,
                externalReference);

        model.addAttribute("title", "Pago Fallido");
        model.addAttribute("message", "Lo sentimos, tu pago no pudo completarse.");
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("status", status);
        model.addAttribute("externalReference", externalReference);

        return "paymentResponse";
    }
}
