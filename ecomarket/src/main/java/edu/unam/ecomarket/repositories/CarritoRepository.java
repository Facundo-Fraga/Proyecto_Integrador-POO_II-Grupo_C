package edu.unam.ecomarket.repositories;

import edu.unam.ecomarket.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CarritoRepository {

    private final Map<Long, Integer> carrito = new HashMap<>();

    @Autowired
    private ProductoRepository productoRepository;

    // Añadir un producto al carrito
    public void añadirProducto(Producto producto) {
        carrito.merge(producto.getIdProducto(), 1, Integer::sum); // Aumentar la cantidad si ya está en el carrito
    }

    // Obtener el total de productos en el carrito
    public int obtenerTotalProductos() {
        return carrito.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Obtener los productos en el carrito con sus cantidades
    public Map<Long, Integer> obtenerCarrito() {
        return carrito;
    }

    // Obtener una lista de productos completos del carrito
    public Map<Producto, Integer> obtenerProductosEnCarrito() {
        Map<Producto, Integer> productosEnCarrito = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : carrito.entrySet()) {
            Long productoId = entry.getKey();
            Integer cantidad = entry.getValue();
            Producto producto = productoRepository.findById(productoId).orElse(null); // Obtener producto por ID
            if (producto != null) {
                productosEnCarrito.put(producto, cantidad);
            }
        }
        return productosEnCarrito;
    }

    // Eliminar un producto del carrito
    public void eliminarProducto(Long idProducto) {
        carrito.remove(idProducto); // Elimina el producto por su ID
    }

    // Método para actualizar la cantidad de un producto
    public void actualizarCantidad(Long idProducto, int cantidad) {
        if (cantidad > 0) {
            carrito.put(idProducto, cantidad); // Actualiza la cantidad en el carrito
        } else {
            carrito.remove(idProducto); // Si la cantidad es 0, elimina el producto
        }
    }

    // Vaciar el carrito
    public void vaciarCarrito() {
        carrito.clear();
    }

}
