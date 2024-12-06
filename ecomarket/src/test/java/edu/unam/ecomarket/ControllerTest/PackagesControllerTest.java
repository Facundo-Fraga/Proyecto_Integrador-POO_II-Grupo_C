package edu.unam.ecomarket.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.PackagesController;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;

@WebMvcTest(PackagesController.class)
public class PackagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    private ProductoPaquete productoPaqueteMock;
    private ProductoSingular productoSingularMock;

    // Subclase concreta para simular `ProductoSingular` con ID
    private static class ProductoSingularConcreto extends ProductoSingular {
        public ProductoSingularConcreto(Long id, String nombre) {
            super();
            this.setNombre(nombre);
            this.getDetalles().put("id", id.toString()); // Usamos un detalle para simular el ID
        }

        public Long getId() {
            return Long.valueOf(this.getDetalles().get("id"));
        }
    }

    @BeforeEach
    public void setUp() {
        // Inicializar mocks
        productoPaqueteMock = new ProductoPaquete();
        productoPaqueteMock.setNombre("Paquete de prueba");

        productoSingularMock = new ProductoSingularConcreto(1L, "Producto singular de prueba");

        // Configurar comportamiento de los mocks
        when(productoService.buscarProductosSingulares()).thenReturn(List.of(productoSingularMock));
        when(productoService.buscarProductoSingularPorId(anyLong())).thenReturn(productoSingularMock);
        when(productoService.buscarPaquetePorId(anyLong())).thenReturn(productoPaqueteMock);
    }

    @Test
    public void testMostrarFormulario() throws Exception {
        mockMvc.perform(get("/packageCreator"))
                .andExpect(status().isOk())
                .andExpect(view().name("packageCreator"))
                .andExpect(model().attributeExists("productosSingulares"))
                .andExpect(model().attributeExists("productoPaquete"));

        verify(productoService, times(1)).buscarProductosSingulares();
    }

    @Test
    public void testCrearPaquete() throws Exception {
        mockMvc.perform(post("/packageCreator/crear")
                .param("productosSeleccionados", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productsManager"));

        verify(productoService, times(1)).buscarProductoSingularPorId(1L);
        verify(productoService, times(1)).cargarProducto(any(ProductoPaquete.class));
    }

    @Test
    public void testEditarPaquete() throws Exception {
        mockMvc.perform(get("/packageEditor/1/editar"))
                .andExpect(status().isOk())
                .andExpect(view().name("packageEditor"))
                .andExpect(model().attributeExists("productoPaquete"))
                .andExpect(model().attributeExists("productosSingulares"));

        verify(productoService, times(1)).buscarPaquetePorId(1L);
        verify(productoService, times(1)).buscarProductosSingulares();
    }

    @Test
    public void testGuardarCambios() throws Exception {
        mockMvc.perform(post("/packageEditor/1/editar")
                .param("productosSeleccionados", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productsManager"));

        verify(productoService, times(1)).buscarProductoSingularPorId(1L);
        verify(productoService, times(1)).actualizarPaquete(anyLong(), any(ProductoPaquete.class));
    }

    @Test
    public void testGuardarCambiosSinProductosSeleccionados() throws Exception {
        mockMvc.perform(post("/packageEditor/1/editar"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productsManager"));

        verify(productoService, times(0)).buscarProductoSingularPorId(anyLong());
        verify(productoService, times(1)).actualizarPaquete(anyLong(), any(ProductoPaquete.class));
    }
}
