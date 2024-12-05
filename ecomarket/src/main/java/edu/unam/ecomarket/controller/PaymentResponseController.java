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

@Controller
@RequestMapping("/response")
public class PaymentResponseController {
    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private HttpSession session;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            return "errorPageCliente"; // Redirige a una vista de error
        }

        try {
            // Recuperar MetodoEnvio desde la sesión
            MetodoEnvio envio = (MetodoEnvio) session.getAttribute("envio");
            if (envio == null) {
                logger.error("No se encontró información de envío en la sesión");
                model.addAttribute("error", "Error: no se pudo encontrar información de envío.");
                return "errorPageCliente"; // Redirige a una vista de error
            }

            // Obtener productos del carrito
            Map<Producto, Integer> productosCarrito = carritoService.obtenerProductosEnCarrito();
            if (productosCarrito.isEmpty()) {
                model.addAttribute("error", "El carrito está vacío.");
                return "errorPageCliente"; // Redirige a una vista de error
            }

            // Crear pedido
            Pedido pedido = pedidoService.crearPedido(cliente, productosCarrito, paymentId, status, envio);
            model.addAttribute("pedido", pedido); // Agrega el pedido a la vista

            // Puedes redirigir a una página de confirmación de pedido
            return "succesfulPayment"; // Vista que muestra que el pedido fue exitoso
        } catch (Exception e) {
            logger.error("Error al procesar la notificación de pago", e);
            model.addAttribute("error", "Ocurrió un error al procesar tu pedido.");
            return "errorPageCliente"; // Redirige a una vista de error
        }
    }

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
