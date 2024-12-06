package edu.unam.ecomarket.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Cliente;
import edu.unam.ecomarket.modelo.DetallePedido;
import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.modelo.PagoMercadoPago;
import edu.unam.ecomarket.modelo.Pedido;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.DetallePedidoRepository;
import edu.unam.ecomarket.repositories.PagoRepository;
import edu.unam.ecomarket.repositories.PedidoRepository;
import edu.unam.ecomarket.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Transactional
    public Pedido crearPedido(Cliente cliente, Map<Producto, Integer> productosCarrito, String idPagoMP, String status,
            MetodoEnvio envio) {
        List<DetallePedido> detalles = new ArrayList<>();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(cliente);
        System.out.println("--------------------------------------------------------------------------------");
        // Crear detalles del pedido
        for (Map.Entry<Producto, Integer> entry : productosCarrito.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();

            DetallePedido detalle = new DetallePedido(cantidad, producto);
            detalles.add(detalle);
            detallePedidoRepository.save(detalle);
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(detalle);
            System.out.println(detalle.getSubTotal());
            System.out.println("--------------------------------------------------------------------------------");
        }
        System.out.println("TERMINO DE RECORRER EL CARRITO");

        // Crear pago
        PagoMercadoPago pago = new PagoMercadoPago(idPagoMP, status);

        pagoRepository.save(pago);
        // Crear pedido
        Pedido pedido = new Pedido(envio, pago, detalles,cliente);
        return pedidoRepository.save(pedido);
    }
}
