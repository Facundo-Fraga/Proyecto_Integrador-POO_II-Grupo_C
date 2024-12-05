package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Descuento;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.DescuentosRepository;
import edu.unam.ecomarket.repositories.EstrategiaRepository;

@Service
public class DescuentosService {

    @Autowired
    protected DescuentosRepository descuentosrepository;

    @Autowired
    protected EstrategiaRepository estrategiaRepository;

    @Autowired
    protected ProductoService servicioProducto;

    public Descuento buscarDescuentoId(Long idDescuento) {
        return descuentosrepository.findById(idDescuento).get();
    }

    public List<Descuento> obtenerDescuentos() {
        return descuentosrepository.findAll();
    }

    public void guardarDescuento(Descuento descuento) {
        descuentosrepository.save(descuento);
    }

    public void eliminarDescuento(Long idDescuento) {
        Descuento descuento = buscarDescuentoId(idDescuento);

        List<Producto> productos = descuento.getProductos();

        for(Producto index : productos) {
            index.getDescuentos().remove(descuento);
            servicioProducto.cargarProducto(index);
        }

        descuentosrepository.deleteById(idDescuento);
    }

    public void persistirEstrategias(EstrategiaDescuento estrategia) {
        estrategiaRepository.save(estrategia);
    }
}
