package edu.unam.ecomarket.modelo;

public class PrecioConDescuento implements EstrategiaPrecio {
    private final double porcentajeDescuento;

    public PrecioConDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 1) {
            throw new IllegalArgumentException(
                "El porcentaje de descuento debe estar entre 0 y 1. Valor recibido: " + porcentajeDescuento
            );
        }
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase * (1 - porcentajeDescuento);
    }
}
