package edu.unam.ecomarket.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.DescuentoController;
import edu.unam.ecomarket.modelo.Descuento;
import edu.unam.ecomarket.modelo.DescuentoFijo;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.DescuentosService;
import edu.unam.ecomarket.services.ProductoService;

@WebMvcTest(DescuentoController.class)

public class DescuentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DescuentosService descuentoService;

    @MockBean
    private ProductoService productoService;

    private Descuento descuento;
    private List<Descuento> listaDescuentos;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        // Subclase concreta de Producto definida en el test
        class ProductoConcreto extends Producto {
            public ProductoConcreto(Long idProducto, String nombre) {
                this.setIdProducto(idProducto);
                this.setNombre(nombre);
            }
        }

        // Configurar objeto de prueba Descuento
        descuento = new Descuento();
        descuento.setIdDescuento(1L);
        descuento.setNombre("Descuento de prueba");
        descuento.setFechaInicio(LocalDate.now().plusDays(1));
        descuento.setFechaFin(LocalDate.now().plusDays(10));

        // Configurar la estrategia
        EstrategiaDescuento estrategia = new DescuentoFijo(); // Usa una subclase concreta
        estrategia.setId(1L);
        estrategia.setValorDescuento(50.0);
        descuento.setEstrategia(estrategia);

        // Agregar el descuento a la lista
        listaDescuentos = new ArrayList<>();
        listaDescuentos.add(descuento);

        // Inicializar productos usando la subclase concreta
        producto1 = new ProductoConcreto(1L, "Producto 1");
        producto2 = new ProductoConcreto(2L, "Producto 2");
    }

    @Test
    public void testIndex() throws Exception {
        when(descuentoService.obtenerDescuentos()).thenReturn(listaDescuentos);

        mockMvc.perform(get("/manageDiscounts"))
                .andExpect(status().isOk())
                .andExpect(view().name("manageDiscounts"))
                .andExpect(model().attributeExists("descuentos"))
                .andExpect(model().attribute("descuentos", listaDescuentos));
    }

    @Test
    public void testEliminarDescuento() throws Exception {
        doNothing().when(descuentoService).eliminarDescuento(1L);

        mockMvc.perform(get("/manageDiscounts/eliminar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manageDiscounts"));
    }

    @Test
    public void testCargarFormularioCreacion() throws Exception {
        List<Producto> productos = List.of(producto1, producto2);
        when(productoService.buscarProductos()).thenReturn(productos);

        mockMvc.perform(get("/discountsCreator"))
                .andExpect(status().isOk())
                .andExpect(view().name("discountsCreator"))
                .andExpect(model().attributeExists("descuento"))
                .andExpect(model().attributeExists("productos"))
                .andExpect(model().attribute("productos", productos));
    }

    @Test
    public void testCrearDescuento() throws Exception {
        List<Long> productosSeleccionados = List.of(1L, 2L);
        List<Producto> productos = List.of(producto1, producto2);
        when(productoService.buscarProductos(productosSeleccionados)).thenReturn(productos);

        mockMvc.perform(post("/discountsCreator/crear")
                .param("estrat", "montoFijo")
                .param("montoFijo", "50.0")
                .param("nombre", "Descuento por monto")
                .param("fechaInicio", LocalDate.now().plusDays(1).toString())
                .param("fechaFin", LocalDate.now().plusDays(10).toString())
                .param("productosSeleccionados", "1", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manageDiscounts"));

        verify(descuentoService, times(1)).guardarDescuento(any(Descuento.class));
    }

}
