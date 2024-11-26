package edu.unam.ecomarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/response")
public class PaymentResponseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam(value = "payment_id", required = false) String paymentId,
                                 @RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "external_reference", required = false) String externalReference,
                                 Model model) {
        logger.info("Pago exitoso recibido: payment_id={}, status={}, external_reference={}", paymentId, status, externalReference);

        model.addAttribute("title", "Pago Exitoso");
        model.addAttribute("message", "¡Gracias por tu compra!");
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("status", status);
        model.addAttribute("externalReference", externalReference);

        return "paymentResponse";
    }

    @GetMapping("/pending")
    public String paymentPending(@RequestParam(value = "payment_id", required = false) String paymentId,
                                 @RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "external_reference", required = false) String externalReference,
                                 Model model) {
        logger.info("Pago pendiente: payment_id={}, status={}, external_reference={}", paymentId, status, externalReference);

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
        logger.info("Pago fallido: payment_id={}, status={}, external_reference={}", paymentId, status, externalReference);

        model.addAttribute("title", "Pago Fallido");
        model.addAttribute("message", "Lo sentimos, tu pago no pudo completarse.");
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("status", status);
        model.addAttribute("externalReference", externalReference);

        return "paymentResponse";
    }
}
