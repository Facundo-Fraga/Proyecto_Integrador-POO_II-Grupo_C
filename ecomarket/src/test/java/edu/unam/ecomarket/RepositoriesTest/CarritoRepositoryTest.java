package edu.unam.ecomarket.RepositoriesTest;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.CarritoRepository;
import edu.unam.ecomarket.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarritoRepositoryTest {

    @InjectMocks
    private CarritoRepository carritoRepository; // La clase a probar

    @Mock
    private ProductoRepository productoRepository; // Dependencia que se simula

    private Producto producto;

    // Subclase concreta de Producto para poder instanciarlo
    public static class ProductoConcreto extends Producto {
        public ProductoConcreto(String nombre, String descripcion, double precioBase) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precioBase = precioBase;
        }
    }

    @BeforeEach
    void setUp() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);

        // Inicializa un producto para usar en las pruebas (usando la subclase concreta)
        producto = new ProductoConcreto("Producto 1", "Descripción del Producto 1", 100.0);
        producto.setIdProducto(1L);
    }

    @Test
    void testAñadirProducto() {
        // Simula que el producto existe en el repositorio
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(java.util.Optional.of(producto));

        // Añadir producto al carrito
        carritoRepository.añadirProducto(producto);

        // Verificar que el carrito contiene el producto con la cantidad correcta
        Map<Long, Integer> carrito = carritoRepository.obtenerCarrito();
        assertEquals(1, carrito.size());
        assertEquals(1, carrito.get(producto.getIdProducto()));
    }

    @Test
    void testObtenerTotalProductos() {
        // Simula que el producto existe
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(java.util.Optional.of(producto));

        // Añadir producto al carrito
        carritoRepository.añadirProducto(producto);
        carritoRepository.añadirProducto(producto); // Se añade otra vez, la cantidad debe ser 2

        // Verificar el total de productos en el carrito
        assertEquals(2, carritoRepository.obtenerTotalProductos());
    }

    @Test
    void testObtenerProductosEnCarrito() {
        // Simula que el producto existe
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(java.util.Optional.of(producto));

        // Añadir producto al carrito
        carritoRepository.añadirProducto(producto);

        // Verificar los productos en el carrito
        Map<Producto, Integer> productosEnCarrito = carritoRepository.obtenerProductosEnCarrito();
        assertEquals(1, productosEnCarrito.size());
        assertTrue(productosEnCarrito.containsKey(producto));
        assertEquals(1, productosEnCarrito.get(producto));
    }

    @Test
    void testEliminarProducto() {
        // Simula que el producto existe
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(java.util.Optional.of(producto));

        // Añadir producto al carrito
        carritoRepository.añadirProducto(producto);

        // Eliminar producto del carrito
        carritoRepository.eliminarProducto(producto.getIdProducto());

        // Verificar que el carrito está vacío
        assertTrue(carritoRepository.obtenerCarrito().isEmpty());
    }

    @Test
    void testActualizarCantidad() {
        // Simula que el producto existe
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(java.util.Optional.of(producto));

        // Añadir producto al carrito
        carritoRepository.añadirProducto(producto);

        // Actualizar la cantidad del producto en el carrito
        carritoRepository.actualizarCantidad(producto.getIdProducto(), 3);

        // Verificar que la cantidad del producto se actualizó correctamente
        Map<Long, Integer> carrito = carritoRepository.obtenerCarrito();
        assertEquals(3, carrito.get(producto.getIdProducto()));
    }

    @Test
    void testVaciarCarrito() {
        // Simula que el producto existe
        when(productoRepository.findById(producto.getIdProducto())).thenReturn(java.util.Optional.of(producto));

        // Añadir producto al carrito
        carritoRepository.añadirProducto(producto);

        // Vaciar el carrito
        carritoRepository.vaciarCarrito();

        // Verificar que el carrito está vacío
        assertTrue(carritoRepository.obtenerCarrito().isEmpty());
    }
}
