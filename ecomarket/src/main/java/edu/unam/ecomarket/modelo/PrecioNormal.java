package edu.unam.ecomarket.modelo;

public class PrecioNormal implements EstrategiaPrecio{
    
    @Override
    public double calcularPrecio(double precioBase){
        return precioBase;
    }
}
