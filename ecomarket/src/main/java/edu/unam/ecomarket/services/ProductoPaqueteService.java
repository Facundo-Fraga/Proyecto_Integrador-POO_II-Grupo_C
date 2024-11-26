package edu.unam.ecomarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.repositories.ProductoPaqueteRepository;

import java.util.List;


import edu.unam.ecomarket.modelo.ProductoPaquete;

@Service
public class ProductoPaqueteService {

    @Autowired
    private ProductoPaqueteRepository productoPaqueteRepositorio;

    // Crear un nuevo producto paquete
    public ProductoPaquete crearProducto(ProductoPaquete producto) {
        return productoPaqueteRepositorio.save(producto);
    }

    // Obtener todos los productos paquete
    public List<ProductoPaquete> obtenerTodos() {
        return productoPaqueteRepositorio.findAll();
    }

    // Obtener un producto paquete por su ID
    public ProductoPaquete obtenerPorId(Long id) {
        return productoPaqueteRepositorio.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion(id));
    }

    // Actualizar un producto paquete
    public ProductoPaquete actualizarProducto(ProductoPaquete producto) {
        if (producto.getIdPaquete() != null && productoPaqueteRepositorio.existsById(producto.getIdPaquete())) {
            return productoPaqueteRepositorio.save(producto);
        } else {
            throw new EntidadNoEncontradaExcepcion(producto.getIdPaquete());
        }
    }

    // Eliminar un producto paquete
    public void eliminarProducto(Long id) {
        if (productoPaqueteRepositorio.existsById(id)) {
            productoPaqueteRepositorio.deleteById(id);
        } else {
            throw new EntidadNoEncontradaExcepcion(id);
        }
    }
}
