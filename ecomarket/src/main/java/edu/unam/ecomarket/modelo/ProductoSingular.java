package edu.unam.ecomarket.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class ProductoSingular extends Producto {

    @ManyToMany
    @JoinTable(
        name = "paquete_producto",
        joinColumns = @JoinColumn(name = "paquete_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<ProductoPaquete> paquetesContenedores = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "producto_detalles",
        joinColumns = @JoinColumn(name = "producto_id")
    )
    @MapKeyColumn(name = "clave")
    @Column(name = "valor")
    protected Map<String, String> detalles = new HashMap<>();

    public void agregarPaqueteContenedor(ProductoPaquete paquete) {
        paquetesContenedores.add(paquete);
        paquete.agregarProducto(this);
    }

    public void eliminarPaqueteContenedor(ProductoPaquete paquete) {
        paquetesContenedores.remove(paquete);
        paquete.eliminarProducto(this);
    }

    public List<ProductoPaquete> getPaquetesContenedores() {
        return paquetesContenedores;
    }
}
