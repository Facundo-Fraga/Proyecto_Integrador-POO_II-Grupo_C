package edu.unam.ecomarket.repositories;

import edu.unam.ecomarket.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Repositorio que gestiona las operaciones relacionadas con el carrito de compras.
 * 
 * <p>
 * Este repositorio proporciona métodos para agregar, eliminar, actualizar y obtener
 * productos del carrito. El carrito se implementa como un mapa en memoria, donde
 * las claves son los IDs de los productos y los valores son las cantidades.
 * </p>
 * 
 * <p>
 * También permite obtener detalles completos de los productos desde la base de datos
 * a través del {@link ProductoRepository}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Repository
public class CarritoRepository {

    /**
     * Mapa que representa el carrito de compras.
     * Las claves son los IDs de los productos, y los valores son las cantidades.
     */
    private final Map<Long, Integer> carrito = new HashMap<>();

    /**
     * Repositorio para acceder a los datos de los productos.
     */
    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Añade un producto al carrito.
     * 
     * <p>
     * Si el producto ya existe en el carrito, aumenta la cantidad en 1.
     * </p>
     * 
     * @param producto Producto que se desea agregar al carrito.
     */
    public void añadirProducto(Producto producto) {
        carrito.merge(producto.getIdProducto(), 1, Integer::sum);
    }

    /**
     * Obtiene el total de productos (sumatoria de cantidades) en el carrito.
     * 
     * @return Total de productos en el carrito.
     */
    public int obtenerTotalProductos() {
        return carrito.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Obtiene los productos en el carrito con sus cantidades.
     * 
     * @return Un mapa donde las claves son los IDs de los productos y los valores son las cantidades.
     */
    public Map<Long, Integer> obtenerCarrito() {
        return carrito;
    }

    /**
     * Obtiene una lista de productos completos con sus cantidades desde el carrito.
     * 
     * <p>
     * Los productos se obtienen del repositorio de productos mediante sus IDs.
     * </p>
     * 
     * @return Un mapa donde las claves son los productos completos y los valores son las cantidades.
     */
    public Map<Producto, Integer> obtenerProductosEnCarrito() {
        Map<Producto, Integer> productosEnCarrito = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : carrito.entrySet()) {
            Long productoId = entry.getKey();
            Integer cantidad = entry.getValue();
            Producto producto = productoRepository.findById(productoId).orElse(null);
            if (producto != null) {
                productosEnCarrito.put(producto, cantidad);
            }
        }
        return productosEnCarrito;
    }

    /**
     * Elimina un producto del carrito.
     * 
     * @param idProducto ID del producto que se desea eliminar.
     */
    public void eliminarProducto(Long idProducto) {
        carrito.remove(idProducto);
    }

    /**
     * Actualiza la cantidad de un producto en el carrito.
     * 
     * <p>
     * Si la cantidad es mayor a 0, actualiza el valor. Si la cantidad es 0, elimina
     * el producto del carrito.
     * </p>
     * 
     * @param idProducto ID del producto cuya cantidad se desea actualizar.
     * @param cantidad   Nueva cantidad del producto.
     */
    public void actualizarCantidad(Long idProducto, int cantidad) {
        if (cantidad > 0) {
            carrito.put(idProducto, cantidad);
        } else {
            carrito.remove(idProducto);
        }
    }

    /**
     * Vacía el carrito eliminando todos los productos.
     */
    public void vaciarCarrito() {
        carrito.clear();
    }
}
