package edu.unam.ecomarket.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.ShoppingCartController;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.services.CarritoService;

@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    private ProductoConcreto productoMock;

    // Subclase concreta de Producto
    private static class ProductoConcreto extends Producto {
        public ProductoConcreto(String nombre, double precioBase) {
            this.nombre = nombre;
            this.precioBase = precioBase;
        }
    }

    @BeforeEach
    public void setUp() {
        productoMock = new ProductoConcreto("Producto de Prueba", 100.0);
    }

    @Test
    public void testVerCarrito() throws Exception {
        Map<Producto, Integer> productosEnCarrito = new HashMap<>();
        productosEnCarrito.put(productoMock, 2);

        when(carritoService.obtenerProductosEnCarrito()).thenReturn(productosEnCarrito);

        mockMvc.perform(get("/shoppingCart"))
                .andExpect(status().isOk())
                .andExpect(view().name("shoppingCart"))
                .andExpect(model().attributeExists("productos"))
                .andExpect(model().attribute("productos", productosEnCarrito));

        verify(carritoService, times(1)).obtenerProductosEnCarrito();
    }

    @Test
    public void testEliminarDelCarrito() throws Exception {
        mockMvc.perform(post("/remove")
                .param("idProducto", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shoppingCart"));

        verify(carritoService, times(1)).eliminarProducto(1L);
    }

    @Test
    public void testAñadirAlCarrito() throws Exception {
        mockMvc.perform(post("/add")
                .param("idProducto", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clientMenu"));

        verify(carritoService, times(1)).añadirProducto(1L);
    }

    @Test
    public void testActualizarCantidad() throws Exception {
        mockMvc.perform(post("/update-cantidad")
                .param("idProducto", "1")
                .param("cantidad", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shoppingCart"));

        verify(carritoService, times(1)).actualizarCantidad(1L, 3);
    }
}
