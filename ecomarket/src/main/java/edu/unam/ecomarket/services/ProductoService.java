package edu.unam.ecomarket.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoPaquete;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.repositories.ProductoRepository;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los productos.
 * 
 * <p>
 * Este servicio incluye operaciones CRUD para los productos, tanto singulares como paquetes.
 * También proporciona lógica específica para manejar relaciones entre paquetes y productos
 * singulares, así como la gestión de detalles de productos.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * <p>
 * Se apoya en el repositorio {@link ProductoRepository} para interactuar con la base de datos.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Service
public class ProductoService {

    @Autowired
    protected ProductoRepository repository;

    /**
     * Guarda o actualiza un producto en la base de datos.
     * 
     * @param producto Producto a guardar o actualizar.
     */
    public void cargarProducto(Producto producto) {
        repository.save(producto);
    }

    /**
     * Busca productos por sus IDs.
     * 
     * @param productosId Lista de IDs de productos a buscar.
     * @return Lista de productos encontrados.
     */
    public List<Producto> buscarProductos(List<Long> productosId) {
        return repository.findAllById(productosId);
    }

    /**
     * Obtiene todos los productos registrados en la base de datos.
     * 
     * @return Lista de todos los productos.
     */
    public List<Producto> buscarProductos() {
        return repository.findAll();
    }

    /**
     * Obtiene todos los productos singulares registrados en la base de datos.
     * 
     * @return Lista de productos singulares.
     */
    public List<ProductoSingular> buscarProductosSingulares() {
        return repository.findAllProductoSingular();
    }

    /**
     * Busca un producto singular por su ID.
     * 
     * @param id ID del producto singular a buscar.
     * @return Producto singular encontrado.
     * @throws NoSuchElementException Si no se encuentra el producto.
     */
    public ProductoSingular buscarProductoSingularPorId(Long id) {
        return repository.findProductoSingularById(id).get();
    }

    /**
     * Busca un paquete de productos por su ID.
     * 
     * @param id ID del paquete a buscar.
     * @return Paquete de productos encontrado.
     * @throws NoSuchElementException Si no se encuentra el paquete.
     */
    public ProductoPaquete buscarPaquetePorId(Long id) {
        return repository.findPaqueteById(id).get();
    }

    /**
     * Busca un producto por su ID, sin importar su tipo.
     * 
     * @param id ID del producto a buscar.
     * @return Producto encontrado.
     * @throws NoSuchElementException Si no se encuentra el producto.
     */
    public Producto buscarProductoId(Long id) {
        return repository.findById(id).get();
    }

    /**
     * Elimina un producto por su ID.
     * 
     * @param id ID del producto a eliminar.
     */
    public void quitarProducto(Long id) {
        repository.deleteById(id);
    }

    /**
     * Crea un producto singular y opcionalmente asigna detalles.
     * 
     * @param productoSingular Producto singular a crear.
     * @param claves           Claves de los detalles (opcional).
     * @param valores          Valores de los detalles (opcional).
     */
    public void crearProductoSingular(ProductoSingular productoSingular, List<String> claves, List<String> valores) {
        try {
            actualizarDetalles(productoSingular, claves, valores);
            cargarProducto(productoSingular);
        } catch (Exception e) {
            cargarProducto(productoSingular);
        }
    }

    /**
     * Actualiza los detalles de un producto singular.
     * 
     * @param productoSingular Producto singular a actualizar.
     * @param claves           Nuevas claves de detalles (opcional).
     * @param valores          Nuevos valores de detalles (opcional).
     */
    public void actualizarProductoSingular(Long id, ProductoSingular productoSingular, @Nullable List<String> claves,
                                           @Nullable List<String> valores) {
        ProductoSingular existente = buscarProductoSingularPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        existente.setNombre(productoSingular.getNombre());
        existente.setPrecioBase(productoSingular.getPrecioBase());
        existente.getDetalles().clear();
        if (claves != null && valores != null) {
            actualizarDetalles(existente, claves, valores);
        }
        if (!existente.getPaquetesContenedores().isEmpty()) {
            for (ProductoPaquete paquete : existente.getPaquetesContenedores()) {
                paquete.recalcularPrecioBase();
            }
        }
        cargarProducto(existente);
    }

    /**
     * Actualiza un paquete de productos.
     * 
     * @param id      ID del paquete a actualizar.
     * @param paquete Nuevos datos del paquete.
     */
    public void actualizarPaquete(Long id, ProductoPaquete paquete) {
        ProductoPaquete existente = buscarPaquetePorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        existente.setNombre(paquete.getNombre());
        existente.getProductos().clear();

        for (ProductoSingular producto : paquete.getProductos()) {
            producto.agregarPaqueteContenedor(existente);
            cargarProducto(producto);
        }

        existente.recalcularPrecioBase();
        cargarProducto(existente);
    }

    private void actualizarDetalles(ProductoSingular producto, List<String> claves, List<String> valores) {
        for (int i = 0; i < claves.size(); i++) {
            producto.getDetalles().put(claves.get(i), valores.get(i));
        }
    }

    /**
     * Elimina un producto singular y actualiza las relaciones con paquetes contenedores.
     * 
     * @param producto Producto singular a eliminar.
     */
    public void eliminarProductoSingular(ProductoSingular producto) {
        if (producto.getPaquetesContenedores().isEmpty()) {
            quitarProducto(producto.getIdProducto());
            return;
        }

        for (ProductoPaquete paquete : producto.getPaquetesContenedores()) {
            producto.eliminarPaqueteContenedor(paquete);
            cargarProducto(paquete);
        }

        quitarProducto(producto.getIdProducto());
    }

    /**
     * Elimina un paquete de productos y actualiza las relaciones con los productos
     * singulares contenidos.
     * 
     * @param paquete Paquete a eliminar.
     */
    public void eliminarPaquete(ProductoPaquete paquete) {
        for (ProductoSingular producto : paquete.getProductos()) {
            producto.eliminarPaqueteContenedor(paquete);
            cargarProducto(producto);
        }

        quitarProducto(paquete.getIdProducto());
    }
}
