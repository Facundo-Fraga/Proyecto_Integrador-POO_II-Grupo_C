package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pago")
public abstract class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_seq")
    @SequenceGenerator(name = "pago_seq", sequenceName = "pago_sequence", allocationSize = 1)
    @Column(name = "id_pago")  // Aseguramos que la columna tenga el nombre adecuado
    private Long idPago;
    
    @Column
    private LocalDateTime fechaPago = LocalDateTime.now(); 
}
