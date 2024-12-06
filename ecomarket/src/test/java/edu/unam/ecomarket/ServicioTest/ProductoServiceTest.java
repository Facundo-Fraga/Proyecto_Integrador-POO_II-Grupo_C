package edu.unam.ecomarket.ServicioTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.repositories.ProductoRepository;
import edu.unam.ecomarket.services.ProductoService;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para verificar que un producto puede ser cargado usando el repositorio
    @Test
    void testCargarProducto() {
        Producto productoMock = mock(Producto.class);

        productoService.cargarProducto(productoMock);

        verify(productoRepository, times(1)).save(productoMock);
    }

    // Test para buscar una lista de productos por sus IDs
    @Test
    void testBuscarProductosPorId() {
        List<Long> ids = List.of(1L, 2L);
        List<Producto> productosMock = List.of(mock(Producto.class), mock(Producto.class));

        when(productoRepository.findAllById(ids)).thenReturn(productosMock);

        List<Producto> productos = productoService.buscarProductos(ids);

        assertNotNull(productos);
        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAllById(ids);
    }

    // Test para buscar todos los productos sin filtros
    @Test
    void testBuscarTodosLosProductos() {
        List<Producto> productosMock = List.of(mock(Producto.class), mock(Producto.class));

        when(productoRepository.findAll()).thenReturn(productosMock);

        List<Producto> productos = productoService.buscarProductos();

        assertNotNull(productos);
        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    // Test para buscar únicamente productos singulares
    @Test
    void testBuscarProductosSingulares() {
        List<ProductoSingular> productosSingularesMock = List.of(mock(ProductoSingular.class));

        when(productoRepository.findAllProductoSingular()).thenReturn(productosSingularesMock);

        List<ProductoSingular> productosSingulares = productoService.buscarProductosSingulares();

        assertNotNull(productosSingulares);
        assertEquals(1, productosSingulares.size());
        verify(productoRepository, times(1)).findAllProductoSingular();
    }

    // Test para buscar un producto singular por ID
    @Test
    void testBuscarProductoSingularPorId() {
        ProductoSingular productoSingularMock = mock(ProductoSingular.class);

        when(productoRepository.findProductoSingularById(1L)).thenReturn(Optional.of(productoSingularMock));

        ProductoSingular productoSingular = productoService.buscarProductoSingularPorId(1L);

        assertNotNull(productoSingular);
        verify(productoRepository, times(1)).findProductoSingularById(1L);
    }

    // Test para buscar un paquete por ID
    @Test
    void testBuscarPaquetePorId() {
        ProductoPaquete paqueteMock = mock(ProductoPaquete.class);

        when(productoRepository.findPaqueteById(1L)).thenReturn(Optional.of(paqueteMock));

        ProductoPaquete paquete = productoService.buscarPaquetePorId(1L);

        assertNotNull(paquete);
        verify(productoRepository, times(1)).findPaqueteById(1L);
    }

    // Test para verificar la eliminación de un producto por ID
    @Test
    void testEliminarProducto() {
        productoService.quitarProducto(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }

    // Test para actualizar un producto singular
    @Test
    void testActualizarProductoSingular() {
        ProductoSingular existente = mock(ProductoSingular.class);
        ProductoSingular nuevo = mock(ProductoSingular.class);

        when(productoRepository.findProductoSingularById(1L)).thenReturn(Optional.of(existente));

        productoService.actualizarProductoSingular(1L, nuevo, List.of("clave"), List.of("valor"));

        verify(productoRepository, times(1)).findProductoSingularById(1L);
        verify(productoRepository, times(1)).save(existente);
    }

    // Test para eliminar un producto singular sin paquetes contenedores
    @Test
    void testEliminarProductoSingular() {
        ProductoSingular productoSingularMock = mock(ProductoSingular.class);
        when(productoSingularMock.getPaquetesContenedores()).thenReturn(new ArrayList<>());

        productoService.eliminarProductoSingular(productoSingularMock);

        verify(productoRepository, times(1)).deleteById(productoSingularMock.getIdProducto());
    }

    // Test para eliminar un paquete vacío
    @Test
    void testEliminarPaquete() {
        ProductoPaquete paqueteMock = mock(ProductoPaquete.class);
        when(paqueteMock.getProductos()).thenReturn(new ArrayList<>());

        productoService.eliminarPaquete(paqueteMock);

        verify(productoRepository, times(1)).deleteById(paqueteMock.getIdProducto());
    }
}
