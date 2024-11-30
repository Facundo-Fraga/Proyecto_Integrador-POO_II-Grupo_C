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

    public void agregarProducto(Producto producto) {
        if (!productos.contains(producto)) { 
            productos.add(producto);
            recalcularPrecioBase();
        }
    }
    
    public void eliminarProducto(Producto producto) {
        if (productos.remove(producto)) {
            recalcularPrecioBase();
        }
    }
    
    private void recalcularPrecioBase() {
        double sumaPrecios = productos.stream().mapToDouble(Producto::getPrecioBase).sum();
        precioBase = sumaPrecios * 0.9;
    }
}