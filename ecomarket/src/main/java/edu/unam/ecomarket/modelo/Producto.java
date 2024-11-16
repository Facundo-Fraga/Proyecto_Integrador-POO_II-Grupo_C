package edu.unam.ecomarket.modelo;

public interface Producto {
    public abstract void aplicarDescuento();
    public void esDisponible();
    // Método para establecer la estrategia de precio
    void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio);

    // Método para calcular el precio final usando la estrategia establecida
    double calcularPrecioFinal(double precioBase);
}
