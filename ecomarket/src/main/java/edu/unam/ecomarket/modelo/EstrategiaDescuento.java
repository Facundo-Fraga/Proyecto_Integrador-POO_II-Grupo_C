package edu.unam.ecomarket.modelo;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_estrategia")
@Getter
@Setter
@NoArgsConstructor
public abstract class EstrategiaDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estrategia_seq")
    @SequenceGenerator(name = "estrategia_seq", sequenceName = "estrategia_sequence", allocationSize = 1)
    private Long id;

    protected Double valorDescuento;

    public abstract Double aplicarDescuento(Double precio);
}
