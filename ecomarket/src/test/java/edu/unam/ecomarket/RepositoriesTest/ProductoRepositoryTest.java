package edu.unam.ecomarket.RepositoriesTest;

import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.repositories.ProductoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductoRepositoryTest {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void testFindAllProductoSingular() {
        // Crear un producto singular directamente sin usar mock
        ProductoSingular productoSingular = new ProductoSingular();
        productoSingular.setIdProducto(1L);
        productoSingular.setNombre("Producto Singular");
        productoSingular.setDescripcion("Descripción Producto Singular");
        productoSingular.setPrecioBase(100.0);

        // Guardar el producto en la base de datos en memoria
        productoRepository.save(productoSingular);

        // Realizar la llamada al método del repositorio
        List<ProductoSingular> productos = productoRepository.findAllProductoSingular();

        // Verificar que la lista de productos sea nulo
        assertNotNull(productos);
        // Verificar los resultados
        assertEquals(1, productos.size());
        assertEquals("Producto Singular", productos.get(0).getNombre());
    }

    @Test
    void testFindProductoSingularById() {
        // Crear un producto singular directamente sin usar mock
        ProductoSingular productoSingular = new ProductoSingular();
        productoSingular.setIdProducto(1L);
        productoSingular.setNombre("Producto Singular");
        productoSingular.setDescripcion("Descripción Producto Singular");
        productoSingular.setPrecioBase(100.0);

        // Guardar el producto en la base de datos en memoria
        productoRepository.save(productoSingular);

        // Realizar la llamada al método del repositorio
        Optional<ProductoSingular> productoOptional = productoRepository.findProductoSingularById(1L);

        // Verificar los resultados
        assertTrue(productoOptional.isPresent());
        assertEquals("Producto Singular", productoOptional.get().getNombre());
    }

    @Test
    void testFindPaqueteById() {
        // Crear un producto paquete directamente sin usar mock
        ProductoPaquete productoPaquete = new ProductoPaquete();
        productoPaquete.setIdProducto(1L);
        productoPaquete.setNombre("Producto Paquete");
        productoPaquete.setDescripcion("Descripción Producto Paquete");
        productoPaquete.setPrecioBase(200.0);

        // Guardar el producto en la base de datos en memoria
        productoRepository.save(productoPaquete);

        // Realizar la llamada al método del repositorio
        Optional<ProductoPaquete> productoOptional = productoRepository.findPaqueteById(1L);

        // Verificar los resultados
        assertTrue(productoOptional.isPresent());
        assertEquals("Producto Paquete", productoOptional.get().getNombre());
    }
}
