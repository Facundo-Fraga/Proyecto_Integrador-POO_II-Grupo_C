package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;

public class PrecioNormal implements EstrategiaPrecio{
    private BigDecimal precio; 
    @Override
    public double calcularPrecio(double precio){
        return precio;
    }
}
