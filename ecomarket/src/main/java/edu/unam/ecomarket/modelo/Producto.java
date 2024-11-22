package edu.unam.ecomarket.modelo;

public interface Producto {
    public abstract void aplicarDescuento();
    public short esDisponible();
    // Método para establecer la estrategia de precio
    void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio);

    
}
