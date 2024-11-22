package edu.unam.ecomarket.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Getter
@NoArgsConstructor

public abstract class MetodoEnvio {
    
    protected TipoEnvio tipo;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "envio_seq")
    @SequenceGenerator(name = "envio_seq", sequenceName = "envio_sequence", allocationSize = 1)

    protected Long nroSeguimiento;

    @Column(nullable = false)

    protected String destino;

    @Column(nullable = false)
    
    protected double tarifaInterna;

    public abstract void seleccionarTipoEnvio();

    public void establecerDestino(String direccion) {
        this.destino = direccion;
    }

    public abstract void calcularCostoBase();

    public final double calcularCostoTotal(String direccion) {
        seleccionarTipoEnvio();
        establecerDestino(direccion);
        calcularCostoBase();
        return tarifaInterna;
    }

}
