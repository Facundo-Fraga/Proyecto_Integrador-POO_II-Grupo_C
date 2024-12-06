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

/**
 * Servicio que gestiona la lógica de negocio relacionada con los pedidos.
 * 
 * <p>
 * Este servicio incluye la creación de pedidos, generación de detalles asociados,
 * registro del pago y la relación con el cliente y el método de envío. Se apoya
 * en los repositorios {@link PedidoRepository}, {@link DetallePedidoRepository},
 * {@link PagoRepository} y {@link UsuarioRepository}.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * <p>
 * Las operaciones críticas están marcadas como {@code @Transactional} para asegurar
 * la consistencia en la base de datos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
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

    /**
     * Crea un pedido completo, incluyendo sus detalles, pago y método de envío.
     * 
     * @param cliente          Cliente que realiza el pedido.
     * @param productosCarrito Mapa de productos y cantidades seleccionadas en el carrito.
     * @param idPagoMP         Identificador del pago en MercadoPago.
     * @param status           Estado del pago en MercadoPago.
     * @param envio            Método de envío seleccionado para el pedido.
     * @return El objeto {@link Pedido} creado y guardado en la base de datos.
     * @throws IllegalArgumentException Si el cliente o los productos del carrito son inválidos.
     */
    @Transactional
    public Pedido crearPedido(Cliente cliente, Map<Producto, Integer> productosCarrito, String idPagoMP, String status,
            MetodoEnvio envio) {
        List<DetallePedido> detalles = new ArrayList<>();

        // Validar datos de entrada
        if (cliente == null || productosCarrito == null || productosCarrito.isEmpty() || envio == null) {
            throw new IllegalArgumentException("Datos insuficientes para crear el pedido.");
        }

        // Crear detalles del pedido
        for (Map.Entry<Producto, Integer> entry : productosCarrito.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();

            DetallePedido detalle = new DetallePedido(cantidad, producto);
            detalles.add(detalle);
            detallePedidoRepository.save(detalle); // Persistir cada detalle
        }

        // Crear y persistir el pago
        PagoMercadoPago pago = new PagoMercadoPago(idPagoMP, status);
        pagoRepository.save(pago);

        // Crear y persistir el pedido
        Pedido pedido = new Pedido(envio, pago, detalles, cliente);
        return pedidoRepository.save(pedido);
    }
}
