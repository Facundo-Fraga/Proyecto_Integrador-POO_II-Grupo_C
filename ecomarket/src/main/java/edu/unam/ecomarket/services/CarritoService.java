package edu.unam.ecomarket.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.CarritoRepository;
import edu.unam.ecomarket.repositories.ProductoRepository;

/**
 * Servicio que gestiona la lógica de negocio relacionada con el carrito de compras.
 * 
 * <p>
 * Proporciona métodos para interactuar con el carrito, incluyendo la adición, eliminación,
 * actualización de cantidades y obtención de productos. Se apoya en los repositorios
 * {@link CarritoRepository} y {@link ProductoRepository} para gestionar los datos.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    /**
     * Constructor para inicializar el servicio con los repositorios requeridos.
     * 
     * @param carritoRepository Repositorio para gestionar los datos del carrito.
     * @param productoRepository Repositorio para acceder a los datos de los productos.
     */
    public CarritoService(CarritoRepository carritoRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene el total de productos en el carrito.
     * 
     * @return Total de productos (sumatoria de cantidades) en el carrito.
     */
    public int obtenerTotalProductos() {
        return carritoRepository.obtenerTotalProductos();
    }

    /**
     * Añade un producto al carrito por su ID.
     * 
     * @param idProducto ID del producto a añadir al carrito.
     * @throws IllegalArgumentException Si el producto no existe.
     */
    public void añadirProducto(Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        carritoRepository.añadirProducto(producto);
    }

    /**
     * Obtiene los productos completos en el carrito, incluyendo detalles como el nombre
     * y la cantidad.
     * 
     * @return Un mapa donde las claves son los productos y los valores son las cantidades.
     */
    public Map<Producto, Integer> obtenerProductosEnCarrito() {
        return carritoRepository.obtenerProductosEnCarrito();
    }

    /**
     * Elimina un producto del carrito por su ID.
     * 
     * @param idProducto ID del producto a eliminar.
     */
    public void eliminarProducto(Long idProducto) {
        carritoRepository.eliminarProducto(idProducto);
    }

    /**
     * Actualiza la cantidad de un producto en el carrito.
     * 
     * @param idProducto ID del producto cuya cantidad se desea actualizar.
     * @param cantidad   Nueva cantidad del producto.
     * @throws IllegalArgumentException Si el producto no existe.
     */
    public void actualizarCantidad(Long idProducto, int cantidad) {
        productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        carritoRepository.actualizarCantidad(idProducto, cantidad);
    }

    /**
     * Vacía completamente el carrito, eliminando todos los productos.
     */
    public void vaciarCarrito() {
        carritoRepository.vaciarCarrito();
    }
}
