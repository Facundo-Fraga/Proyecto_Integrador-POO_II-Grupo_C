package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "pagoTarjeta")


public class PagoConTarjeta implements MetodoPago {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paypal_seq")
    @SequenceGenerator(name = "paypal_seq", sequenceName = "paypal_sequence", allocationSize = 1)

    private Long idTransaccion;

    @Column(nullable = false)

    private BigDecimal monto;

    @Column(nullable = false)

    private BigDecimal saldo;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @NotBlank

    private String moneda;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @NotBlank

    private String numeroTarjeta;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @NotBlank

    private String nombreTitular;
    
    @Column(nullable = false)
    private Date fechaExpiracion;
    
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @NotBlank

    private String cvv;
    

   
    @Override
    public boolean pagar(BigDecimal monto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagar'");
    }
    @Override
    public void obtenerDetalles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerDetalles'");
    }

    
    
}
