package edu.unam.ecomarket.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class ProductoPaquete extends Producto{
    
    @ManyToMany
    @JoinTable(
        name = "paquete_producto",
        joinColumns = @JoinColumn(name = "paquete_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos = new ArrayList<>();

    public ProductoPaquete(String nombre, Map<String, String> detalles) {
        this.nombre = nombre;
        this.detalles = detalles;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public double getPrecio() {
        double sumaPrecios = productos.stream().mapToDouble(Producto::getPrecio).sum();
        return sumaPrecios * 0.9;
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        for (Producto producto : productos) {
            producto.aplicarDescuento(porcentaje);
        }
    }
}

