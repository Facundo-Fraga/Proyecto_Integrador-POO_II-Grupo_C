package edu.unam.ecomarket.modelo;

import java.util.List;

public interface Descuento {
    boolean esAplicable(Producto producto);
    void aplicarSingular(Producto producto);
    void aplicarMultiple(List<Producto> productos);
    boolean estaVigente();
}
