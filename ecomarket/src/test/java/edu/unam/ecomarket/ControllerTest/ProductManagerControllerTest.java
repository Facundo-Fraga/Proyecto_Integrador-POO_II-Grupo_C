package edu.unam.ecomarket.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.ProductsManagerController;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.services.ProductoService;

@WebMvcTest(ProductsManagerController.class)
public class ProductManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    private ProductoSingular productoSingularMock;
    private ProductoPaquete productoPaqueteMock;

    @BeforeEach
    public void setUp() {
        productoSingularMock = new ProductoSingular();
        productoSingularMock.setNombre("Producto Singular");
        productoSingularMock.setPrecioBase(100.0);

        productoPaqueteMock = new ProductoPaquete();
        productoPaqueteMock.setNombre("Producto Paquete");
        productoPaqueteMock.setPrecioBase(200.0);

        when(productoService.buscarProductos()).thenReturn(List.of(productoSingularMock, productoPaqueteMock));
        when(productoService.buscarProductoId(1L)).thenReturn(productoSingularMock);
        when(productoService.buscarProductoId(2L)).thenReturn(productoPaqueteMock);
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/productsManager"))
                .andExpect(status().isOk())
                .andExpect(view().name("productsManager"))
                .andExpect(model().attributeExists("productos"));

        verify(productoService, times(1)).buscarProductos();
    }

    @Test
    public void testEliminarProductoSingular() throws Exception {
        when(productoService.buscarProductoId(1L)).thenReturn(productoSingularMock);

        mockMvc.perform(get("/productsManager/eliminar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productsManager"));

        verify(productoService, times(1)).buscarProductoId(1L);
        verify(productoService, times(1)).eliminarProductoSingular(productoSingularMock);
    }

    @Test
    public void testEliminarProductoPaquete() throws Exception {
        when(productoService.buscarProductoId(2L)).thenReturn(productoPaqueteMock);

        mockMvc.perform(get("/productsManager/eliminar/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productsManager"));

        verify(productoService, times(1)).buscarProductoId(2L);
        verify(productoService, times(1)).eliminarPaquete(productoPaqueteMock);
    }

    @Test
    public void testCargarDetallesProducto() throws Exception {
        mockMvc.perform(get("/productDetails/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productDetails"))
                .andExpect(model().attributeExists("producto"))
                .andExpect(model().attribute("producto", productoSingularMock));

        verify(productoService, times(1)).buscarProductoId(1L);
    }

    @Test
    public void testCargarDetallesPaquete() throws Exception {
        mockMvc.perform(get("/packageDetails/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("packageDetails"))
                .andExpect(model().attributeExists("productoPaquete"))
                .andExpect(model().attribute("productoPaquete", productoPaqueteMock));

        verify(productoService, times(1)).buscarProductoId(2L);
    }
}
