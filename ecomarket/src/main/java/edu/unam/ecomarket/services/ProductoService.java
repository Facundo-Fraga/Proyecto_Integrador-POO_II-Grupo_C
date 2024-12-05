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

@Service
public class ProductoService {

    @Autowired
    protected ProductoRepository repository;

    public void cargarProducto(Producto producto) {
        repository.save(producto);
    }

    public List<Producto> buscarProductos(List<Long> productosId) {
        return repository.findAllById(productosId);
    }


    public List<Producto> buscarProductos() {
        return repository.findAll();
    }

    public List<ProductoSingular> buscarProductosSingulares() {
        return repository.findAllProductoSingular();
    }

    public ProductoSingular buscarProductoSingularPorId(Long id) {
        return repository.findProductoSingularById(id).get();
    }

    public ProductoPaquete buscarPaquetePorId(Long id) {
        return repository.findPaqueteById(id).get();
    }

    public Producto buscarProductoId(Long id) {
        return repository.findById(id).get();
    }

    public void quitarProducto(Long id) {
        repository.deleteById(id);
    }

    public void crearProductoSingular(ProductoSingular productoSingular, List<String> claves, List<String> valores) {
        try {
            actualizarDetalles(productoSingular, claves, valores);
            cargarProducto(productoSingular);
        } catch (Exception e) {
            cargarProducto(productoSingular);
        }
    }

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
            List<ProductoPaquete> paquetes = new ArrayList<>();
            paquetes = existente.getPaquetesContenedores();
            for (ProductoPaquete paquete : paquetes) {
                paquete.recalcularPrecioBase();
            }
        }
        cargarProducto(existente);
    }

    public void actualizarPaquete(Long id, ProductoPaquete paquete) {
        ProductoPaquete existente = buscarPaquetePorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        existente.setNombre(paquete.getNombre());
        
        List<ProductoSingular> productos = new ArrayList<>(existente.getProductos());
        for (ProductoSingular producto : productos) {
            producto.eliminarPaqueteContenedor(existente);
            cargarProducto(producto);
        }
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

    public void eliminarProductoSingular(ProductoSingular producto) {
        System.out.println(producto.getPaquetesContenedores());

        if (producto.getPaquetesContenedores().isEmpty()) {
            quitarProducto(producto.getIdProducto());
            return;
        }

        List<ProductoPaquete> paquetesContenedores = new ArrayList<>(producto.getPaquetesContenedores());
        for (ProductoPaquete paquete : paquetesContenedores) {
            producto.eliminarPaqueteContenedor(paquete);
            cargarProducto(paquete);
        }

        quitarProducto(producto.getIdProducto());
    }

    public void eliminarPaquete(ProductoPaquete paquete) {
        if (paquete.getProductos().isEmpty() || paquete.getProductos() == null) {
            quitarProducto(paquete.getIdProducto());
            return;
        }

        List<ProductoSingular> productos = new ArrayList<>(paquete.getProductos());
        for (ProductoSingular producto : productos) {
            producto.eliminarPaqueteContenedor(paquete);
            cargarProducto(producto);
        }

        quitarProducto(paquete.getIdProducto());
    }

}
