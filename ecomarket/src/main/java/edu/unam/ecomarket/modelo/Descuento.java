package edu.unam.ecomarket.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "descuentos")
@Setter @Getter
@NoArgsConstructor
@Entity
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "descuento_seq")
    @SequenceGenerator(name = "descuento_seq", sequenceName = "descuento_sequence", allocationSize = 1)
    private Long idDescuento;

    @Column(nullable = false)
    @NotBlank
    private String nombre;
    
    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "estrategia_id", nullable = false)
    private EstrategiaDescuento estrategia;

    @ManyToMany(mappedBy = "descuentos")
    @Setter(AccessLevel.NONE)
    private List<Producto> productos;

    public boolean setProductos(List<Producto> productos) {
        try {
            List<Producto> copiaProductos = productos;
            for(Producto index : copiaProductos) {
                index.getDescuentos().add(this);
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean esAplicable() {
            LocalDate hoy = LocalDate.now();
            
            return (hoy.isEqual(fechaInicio) || hoy.isAfter(fechaInicio)) &&
            (hoy.isBefore(fechaFin) || hoy.isEqual(fechaFin));
    }

    public Double aplicarDescuento(Double precio) {
        if (estrategia instanceof DescuentoPorcentaje) {
            return estrategia.aplicarDescuento(precio);
        }
        if(estrategia instanceof DescuentoFijo) {
            return estrategia.aplicarDescuento(precio);
        }
        return 0.0;
    }

    public Double getValorDescuento() {
        return estrategia.getValorDescuento();
    }
}
