package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.modelo.ProductoSingular;
import edu.unam.ecomarket.repositories.ProductoRepository;

@Service
public class ProductoService {

    ProductoRepository repository;

    @Autowired
    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public void cargarProducto(Producto producto) {
        repository.save(producto);
    }

    public List<Producto> buscarProductos() {
        return repository.findAll();
    }

    public Producto buscarProductoId(Long id) {
        return repository.findById(id).get();
    }

    public void quitarProducto(Long id) {
        repository.deleteById(id);
    }

    public void crearProductoConDetalles(ProductoSingular productoSingular, List<String> claves, List<String> valores) {
        try {
            actualizarDetalles(productoSingular, claves, valores);
            cargarProducto(productoSingular);
        } catch (Exception e) {
            cargarProducto(productoSingular);
        }
    }

    public void actualizarProductoConDetalles(Long id, ProductoSingular productoSingular, List<String> claves, List<String> valores) {
        Producto existente = buscarProductoId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        existente.setNombre(productoSingular.getNombre());
        existente.setPrecio(productoSingular.getPrecio());
        existente.getDetalles().clear();
        actualizarDetalles(existente, claves, valores);
        cargarProducto(existente);
    }

    private void actualizarDetalles(Producto producto, List<String> claves, List<String> valores) {
        for (int i = 0; i < claves.size(); i++) {
            producto.getDetalles().put(claves.get(i), valores.get(i));
        }
    }
}
