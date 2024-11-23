package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PrecioConDescuento implements EstrategiaPrecio {

    private final double porcentajeDescuento;
    private final LocalDateTime inicioDescuento;
    private final LocalDateTime finDescuento;
    private BigDecimal precioBase; 
    
    public PrecioConDescuento(double porcentajeDescuento, LocalDateTime inicioDescuento, LocalDateTime finDescuento, BigDecimal precioBase ) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 1) {
            throw new IllegalArgumentException(
                "El porcentaje de descuento debe estar entre 0 y 1. Valor recibido: " + porcentajeDescuento
            );
        }
        this.porcentajeDescuento = porcentajeDescuento;
        this.inicioDescuento = inicioDescuento;
        this.finDescuento = finDescuento;
    }

    @Override
    public double calcularPrecio() {
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isAfter(this.inicioDescuento) && ahora.isBefore(this.finDescuento)) {
            // Aplica el descuento si está en el rango de fechas
            return precioBase * (1 - porcentajeDescuento);  
        } else {
            // Si no está en rango, no aplica el descuento
            return precioBase;  
        }
    }
    
    // Opción adicional: Si alguna vez necesitas usar las fechas explícitamente
    public double calcularPrecio(double precioBase, LocalDateTime inicioDescuento, LocalDateTime finDescuento) {
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isAfter(inicioDescuento) && ahora.isBefore(finDescuento)) {
            return precioBase * (1 - porcentajeDescuento);  // Aplica descuento si está en el rango de fechas
        } else {
            return precioBase;  // Si no está en rango, no aplica descuento
        }
    }
}
