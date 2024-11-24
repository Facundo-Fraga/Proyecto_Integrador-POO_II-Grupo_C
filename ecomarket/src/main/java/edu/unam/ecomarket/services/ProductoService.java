package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Producto;
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

    public void quitarProducto(Long id) {
        repository.deleteById(id);
    }
}
