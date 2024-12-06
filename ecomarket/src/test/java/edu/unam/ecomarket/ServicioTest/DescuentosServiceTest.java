package edu.unam.ecomarket.ServicioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unam.ecomarket.modelo.Descuento;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.DescuentosService;
import edu.unam.ecomarket.services.ProductoService;
import edu.unam.ecomarket.repositories.DescuentosRepository;
import edu.unam.ecomarket.repositories.EstrategiaRepository;

class DescuentosServiceTest {

    // Subclase concreta de Producto para los tests
    public static class ProductoConcreto extends Producto {
        public ProductoConcreto(String nombre, String descripcion, double precioBase) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precioBase = precioBase;
        }
    }

    @Mock
    private DescuentosRepository descuentosRepository;

    @Mock
    private EstrategiaRepository estrategiaRepository;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private DescuentosService descuentosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarDescuentoId() {
        Descuento descuentoMock = new Descuento();
        descuentoMock.setIdDescuento(1L);

        when(descuentosRepository.findById(1L)).thenReturn(Optional.of(descuentoMock));

        Descuento descuento = descuentosService.buscarDescuentoId(1L);

        assertNotNull(descuento);
        assertEquals(1L, descuento.getIdDescuento());
        verify(descuentosRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerDescuentos() {
        Descuento descuento1 = new Descuento();
        Descuento descuento2 = new Descuento();
        List<Descuento> descuentosMock = Arrays.asList(descuento1, descuento2);

        when(descuentosRepository.findAll()).thenReturn(descuentosMock);

        List<Descuento> descuentos = descuentosService.obtenerDescuentos();

        assertNotNull(descuentos);
        assertEquals(2, descuentos.size());
        verify(descuentosRepository, times(1)).findAll();
    }

    @Test
    void testGuardarDescuento() {
        Descuento descuentoMock = new Descuento();

        descuentosService.guardarDescuento(descuentoMock);

        verify(descuentosRepository, times(1)).save(descuentoMock);
    }

    @Test
    void testEliminarDescuento() {
        // Crear un producto mock
        Producto productoMock = new ProductoConcreto("Producto 1", "Descripción", 100.0);

        // Crear un descuento mock
        Descuento descuentoMock = mock(Descuento.class);

        // Configurar la lista de productos para el descuento mock
        List<Producto> productosMock = Arrays.asList(productoMock);

        // Configurar el comportamiento del descuentoMock
        when(descuentoMock.getProductos()).thenReturn(productosMock);
        when(descuentosRepository.findById(1L)).thenReturn(Optional.of(descuentoMock));

        // Ejecutar el método del servicio - Aqui esta la clave del error para que el test falle - Pendiente
        descuentosService.eliminarDescuento(1L);

        // Verificar interacciones con los mocks
        verify(productoService, times(1)).cargarProducto(productoMock);
        verify(descuentosRepository, times(1)).deleteById(1L);
    }


    @Test
    void testPersistirEstrategias() {
        EstrategiaDescuento estrategiaMock = mock(EstrategiaDescuento.class);

        descuentosService.persistirEstrategias(estrategiaMock);

        verify(estrategiaRepository, times(1)).save(estrategiaMock);
    }
}
