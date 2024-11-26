package edu.unam.ecomarket.modelo;

import java.util.Map;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ProductoSingular extends Producto {
    
    public ProductoSingular(String nombre, Map<String, String> detalles, double precio) {
        this.nombre = nombre;
        this.detalles = detalles;
        this.precio = precio;
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        this.precio -= this.precio * (porcentaje / 100);
    }
}
