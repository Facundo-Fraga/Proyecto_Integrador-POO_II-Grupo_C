package edu.unam.ecomarket.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.services.ProductoService;
import edu.unam.ecomarket.controller.ClientMenuController;
import edu.unam.ecomarket.modelo.Producto; // Asumiendo que Producto es la entidad de producto

@WebMvcTest(ClientMenuController.class)
public class ClientMenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    private Producto producto1;
    private Producto producto2;
    private List<Producto> listaProductos;

    // Subclase concreta de Producto para poder instanciarlo
    public static class ProductoConcreto extends Producto {
        public ProductoConcreto(String nombre, String descripcion, double precioBase) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precioBase = precioBase;
        }
    }

    @BeforeEach
    public void setUp() {
        // Inicializar datos de prueba con la subclase concreta
        producto1 = new ProductoConcreto("Producto A", "Descripción A", 100.0);
        producto2 = new ProductoConcreto("Producto B", "Descripción B", 150.0);

        // Aquí no se establece manualmente el ID, ya que se simula que la base de datos lo asigna
        // Lista de productos para el test
        listaProductos = new ArrayList<>();
        listaProductos.add(producto1);
        listaProductos.add(producto2);
    }

    @Test
    public void testCargarMenuCliente() throws Exception {
        // Simulamos la respuesta del servicio
        when(productoService.buscarProductos()).thenReturn(listaProductos);

        // Realizamos la petición GET al endpoint
        mockMvc.perform(get("/clientMenu"))
            .andExpect(status().isOk()) // Verificamos que la respuesta es 200 OK
            .andExpect(view().name("clientMenu")) // Verificamos que la vista es "clientMenu"
            .andExpect(model().attributeExists("productos")) // Verificamos que el modelo contiene "productos"
            .andExpect(model().attribute("productos", listaProductos)); // Verificamos que "productos" tiene la lista esperada
    }
}
