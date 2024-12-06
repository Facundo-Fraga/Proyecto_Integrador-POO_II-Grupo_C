package edu.unam.ecomarket.ServicioTest;

import edu.unam.ecomarket.RepositoriesTest.CarritoRepositoryTest.ProductoConcreto;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.CarritoRepository;
import edu.unam.ecomarket.repositories.ProductoRepository;
import edu.unam.ecomarket.services.CarritoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CarritoServiceTest {

    // Mock de CarritoRepository, usado para simular su comportamiento
    @Mock
    private CarritoRepository carritoRepository;

    // Mock de ProductoRepository, usado para simular operaciones relacionadas con productos
    @Mock
    private ProductoRepository productoRepository;

    // Clase que estamos probando. Se inyectarán los mocks en esta instancia.
    @InjectMocks
    private CarritoService carritoService;

    // Configuración inicial que se ejecuta antes de cada prueba
    @BeforeEach
    void setUp() {
        // Inicializa los mocks declarados en la clase
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Prueba el método obtenerTotalProductos().
     * Verifica que el servicio devuelva el total correcto de productos del carrito.
     */
    @Test
    void testObtenerTotalProductos() {
        // Simula que el repositorio devuelve 5 como total de productos
        when(carritoRepository.obtenerTotalProductos()).thenReturn(5);

        // Llama al método del servicio
        int total = carritoService.obtenerTotalProductos();

        // Verifica que el total devuelto sea el esperado
        assertEquals(5, total);

        // Confirma que el método obtenerTotalProductos() del repositorio se llamó una vez
        verify(carritoRepository, times(1)).obtenerTotalProductos();
    }

    /**
     * Prueba el método añadirProducto() en un caso exitoso.
     * Verifica que un producto válido sea agregado al carrito.
     */
    @Test
    void testAñadirProducto() {
        // Configura un producto de ejemplo
        Producto producto = new ProductoConcreto("Producto de prueba", "Descripción de prueba", 100.0);
        Long idProducto = 1L;

        // Simula que el repositorio encuentra el producto por ID
        when(productoRepository.findById(idProducto)).thenReturn(Optional.of(producto));

        // Llama al método del servicio
        carritoService.añadirProducto(idProducto);

        // Verifica que el repositorio se llamó correctamente
        verify(productoRepository, times(1)).findById(idProducto);
        verify(carritoRepository, times(1)).añadirProducto(producto);
    }

    /**
     * Prueba el método añadirProducto() cuando el producto no existe.
     * Verifica que se lance una excepción si el producto no se encuentra.
     */
    @Test
    void testAñadirProductoNotFound() {
        // Simula que el repositorio no encuentra el producto
        when(productoRepository.findById(2L)).thenReturn(Optional.empty());

        // Llama al método y espera que lance una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> carritoService.añadirProducto(2L));
        assertEquals("Producto no encontrado", exception.getMessage());

        // Verifica que el repositorio fue consultado pero no se intentó agregar un producto
        verify(productoRepository, times(1)).findById(2L);
        verify(carritoRepository, never()).añadirProducto(any());
    }

    /**
     * Prueba el método obtenerProductosEnCarrito().
     * Verifica que el servicio devuelva correctamente los productos en el carrito.
     */
    @Test
    void testObtenerProductosEnCarrito() {
        // Configura un mapa de productos de ejemplo
        Map<Producto, Integer> productosMock = new HashMap<>();
        Producto producto = new ProductoConcreto("Producto de prueba", "Descripción de prueba", 100.0);
        productosMock.put(producto, 3);

        // Simula que el repositorio devuelve este mapa
        when(carritoRepository.obtenerProductosEnCarrito()).thenReturn(productosMock);

        // Llama al método del servicio
        Map<Producto, Integer> productos = carritoService.obtenerProductosEnCarrito();

        // Verifica que el mapa no sea nulo y contenga los valores esperados
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals(3, productos.get(producto));

        // Verifica que el método obtenerProductosEnCarrito() del repositorio se llamó una vez
        verify(carritoRepository, times(1)).obtenerProductosEnCarrito();
    }


    /**
     * Prueba el método eliminarProducto().
     * Verifica que un producto se elimine correctamente del carrito.
     */
    @Test
    void testEliminarProducto() {
        // Llama al método del servicio
        carritoService.eliminarProducto(1L);

        // Verifica que el método eliminarProducto() del repositorio se llamó con el ID correcto
        verify(carritoRepository, times(1)).eliminarProducto(1L);
    }

    /**
     * Prueba el método actualizarCantidad() en un caso exitoso.
     * Verifica que se actualice la cantidad de un producto existente.
     */
    @Test
    void testActualizarCantidad() {
        // Configura un producto de ejemplo
        Producto producto = new ProductoConcreto("Producto de prueba", "Descripción de prueba", 100.0);

        // Simula que el repositorio encuentra el producto por ID
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Llama al método del servicio
        carritoService.actualizarCantidad(1L, 5);

        // Verifica que el método findById() se llamó correctamente
        verify(productoRepository, times(1)).findById(1L);

        // Verifica que el método actualizarCantidad() del repositorio se llamó con los valores correctos
        verify(carritoRepository, times(1)).actualizarCantidad(1L, 5);
    }


    /**
     * Prueba el método actualizarCantidad() cuando el producto no existe.
     * Verifica que se lance una excepción si el producto no se encuentra.
     */
    @Test
    void testActualizarCantidadProductoNoEncontrado() {
        // Simula que el repositorio no encuentra el producto
        when(productoRepository.findById(2L)).thenReturn(Optional.empty());

        // Llama al método y espera que lance una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> carritoService.actualizarCantidad(2L, 3));
        assertEquals("Producto no encontrado", exception.getMessage());

        // Verifica que no se llamó actualizarCantidad() en el repositorio
        verify(productoRepository, times(1)).findById(2L);
        verify(carritoRepository, never()).actualizarCantidad(anyLong(), anyInt());
    }

    /**
     * Prueba el método vaciarCarrito().
     * Verifica que todos los productos sean eliminados del carrito.
     */
    @Test
    void testVaciarCarrito() {
        // Llama al método del servicio
        carritoService.vaciarCarrito();

        // Verifica que el método vaciarCarrito() del repositorio se llamó una vez
        verify(carritoRepository, times(1)).vaciarCarrito();
    }
}
