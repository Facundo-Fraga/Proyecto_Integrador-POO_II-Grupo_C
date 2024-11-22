package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductoPaquete implements Producto{
    private Long idPaquete;
    private String nombre;
    private String descripcion;
    private BigDecimal precioTotal;
    private ArrayList<ProductoIndividual> productos;

    public ProductoPaquete(String nombre, String descripcion, BigDecimal precioTotal,
            ArrayList<ProductoIndividual> productos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioTotal = precioTotal;
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ArrayList<ProductoIndividual> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoIndividual> productos) {
        this.productos = productos;
    }

    public boolean esPaquete() {
        return true;
    }

    @Override
    public void aplicarDescuento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aplicarDescuento'");
    }

    @Override
    public void esDisponible() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esDisponible'");
    }

    @Override
    public void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstrategiaPrecio'");
    }

    @Override
    public double calcularPrecioFinal(double precioBase) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularPrecioFinal'");
    }

}
