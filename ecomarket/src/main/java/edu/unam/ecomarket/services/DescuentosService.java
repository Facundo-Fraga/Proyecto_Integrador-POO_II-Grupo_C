package edu.unam.ecomarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unam.ecomarket.modelo.Descuento;
import edu.unam.ecomarket.modelo.EstrategiaDescuento;
import edu.unam.ecomarket.modelo.Producto;
import edu.unam.ecomarket.repositories.DescuentosRepository;
import edu.unam.ecomarket.repositories.EstrategiaRepository;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los descuentos.
 * 
 * <p>
 * Proporciona métodos para crear, buscar, actualizar y eliminar descuentos,
 * así como para persistir estrategias de descuento en la base de datos.
 * </p>
 * 
 * <p>
 * Se apoya en los repositorios {@link DescuentosRepository} y {@link EstrategiaRepository}
 * para manejar los datos y en el servicio {@link ProductoService} para actualizar
 * las relaciones con los productos.
 * </p>
 * 
 * <p>
 * Diseñado para ser utilizado como un componente de Spring con la anotación {@code @Service}.
 * </p>
 * 
 * @author Grupo C
 * @version 1.0
 */
@Service
public class DescuentosService {

    @Autowired
    protected DescuentosRepository descuentosrepository;

    @Autowired
    protected EstrategiaRepository estrategiaRepository;

    @Autowired
    protected ProductoService servicioProducto;

    /**
     * Busca un descuento por su ID.
     * 
     * @param idDescuento ID del descuento a buscar.
     * @return El objeto {@link Descuento} correspondiente al ID proporcionado.
     * @throws NoSuchElementException Si no se encuentra el descuento.
     */
    public Descuento buscarDescuentoId(Long idDescuento) {
        return descuentosrepository.findById(idDescuento).get();
    }

    /**
     * Obtiene todos los descuentos almacenados en la base de datos.
     * 
     * @return Lista de todos los descuentos.
     */
    public List<Descuento> obtenerDescuentos() {
        return descuentosrepository.findAll();
    }

    /**
     * Guarda o actualiza un descuento en la base de datos.
     * 
     * @param descuento El descuento que se desea guardar o actualizar.
     */
    public void guardarDescuento(Descuento descuento) {
        descuentosrepository.save(descuento);
    }

    /**
     * Elimina un descuento por su ID.
     * 
     * <p>
     * Antes de eliminar el descuento, actualiza las relaciones con los productos
     * para asegurar la integridad de los datos.
     * </p>
     * 
     * @param idDescuento ID del descuento a eliminar.
     */
    public void eliminarDescuento(Long idDescuento) {
        Descuento descuento = buscarDescuentoId(idDescuento);

        List<Producto> productos = descuento.getProductos();

        for (Producto index : productos) {
            index.getDescuentos().remove(descuento);
            servicioProducto.cargarProducto(index);
        }

        descuentosrepository.deleteById(idDescuento);
    }

    /**
     * Persiste una estrategia de descuento en la base de datos.
     * 
     * @param estrategia La estrategia de descuento que se desea guardar.
     */
    public void persistirEstrategias(EstrategiaDescuento estrategia) {
        estrategiaRepository.save(estrategia);
    }
}
