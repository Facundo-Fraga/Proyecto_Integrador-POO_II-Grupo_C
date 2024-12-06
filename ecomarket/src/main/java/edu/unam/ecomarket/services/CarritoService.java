package edu.unam.ecomarket.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.CarritoRepository;
import edu.unam.ecomarket.repositories.ProductoRepository;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public CarritoService(CarritoRepository carritoRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    // Método para obtener el total de productos en el carrito
    public int obtenerTotalProductos() {
        return carritoRepository.obtenerTotalProductos();
    }

    public void añadirProducto(Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        carritoRepository.añadirProducto(producto);
    }

    // Obtener los productos completos del carrito (con detalles)
    public Map<Producto, Integer> obtenerProductosEnCarrito() {
        return carritoRepository.obtenerProductosEnCarrito();
    }

    // Método para eliminar un producto del carrito
    public void eliminarProducto(Long idProducto) {
        carritoRepository.eliminarProducto(idProducto);
    }

    public void actualizarCantidad(Long idProducto, int cantidad) {
        productoRepository.findById(idProducto)
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        
        
            carritoRepository.actualizarCantidad(idProducto, cantidad);
        
    }   
    public void vaciarCarrito() {
        carritoRepository.vaciarCarrito();
    }
    
}
