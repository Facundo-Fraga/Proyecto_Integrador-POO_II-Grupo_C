package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.ProductoIndividual;
import edu.unam.ecomarket.repositories.ProductoIndividualRepository;

@Service
public class ProductoIndividualService {
    
    @Autowired
    private ProductoIndividualRepository productoIndividualRepository;

    // Crear un nuevo producto individual
    public ProductoIndividual crearProducto(ProductoIndividual producto) {
        return productoIndividualRepository.save(producto);
    }

    // Obtener todos los productos
    public List<ProductoIndividual> obtenerTodos() {
        return productoIndividualRepository.findAll();
    }

    // Obtener un producto por su ID
    public ProductoIndividual obtenerPorId(Long id) {
        return productoIndividualRepository.findById(id).
        orElseThrow(() -> new EntidadNoEncontradaExcepcion(id));
    }

    // Actualizar un producto individual
    public ProductoIndividual actualizarProducto(ProductoIndividual producto) {
        if (producto.getIdProducto() != null && productoIndividualRepository.existsById(producto.getIdProducto())) {
            return productoIndividualRepository.save(producto);
        } else {
            return null; // O lanzar una excepción dependiendo de la lógica
        }
    }

    // Eliminar un producto individual
    public void eliminarProducto(Long id) {
        if (productoIndividualRepository.existsById(id)) {
            productoIndividualRepository.deleteById(id);
        } else {
            // Manejar el caso donde no existe el producto
            throw new EntidadNoEncontradaExcepcion(id);
        }
    }
    
}
