package edu.unam.ecomarket.modelo;

import java.time.LocalDate;
import java.util.List;

public class DescuentoPorcentaje implements Descuento {
    private String nombre;
    private double porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public DescuentoPorcentaje(String nombre, double porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean esAplicable(Producto producto) {
        return true; // Por simplicidad, aplica a todos
    }

    @Override
    public void aplicarSingular(Producto producto) {
        producto.aplicarDescuento(porcentaje);
    }

    @Override
    public void aplicarMultiple(List<Producto> productos) {
        for(Producto producto : productos) {
            producto.aplicarDescuento(porcentaje);
        }
    }

    @Override
    public boolean estaVigente() {
        LocalDate hoy = LocalDate.now();
        return (hoy.isAfter(fechaInicio) || hoy.isEqual(fechaInicio)) &&
               (hoy.isBefore(fechaFin) || hoy.isEqual(fechaFin));
    }
}
