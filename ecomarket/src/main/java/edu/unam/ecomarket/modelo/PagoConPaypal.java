package edu.unam.ecomarket.modelo;



import java.math.BigDecimal;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "pagoPaypal")

public class PagoConPaypal implements MetodoPago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paypal_seq")
    @SequenceGenerator(name = "paypal_seq", sequenceName = "paypal_sequence", allocationSize = 1)

    private Long idTransaccion;
   
    @Column(name = "monto", precision = 19, scale = 2, nullable = false)

    private BigDecimal monto;

    @Column(name = "saldo", precision = 19, scale = 2, nullable = false)

    private BigDecimal saldo;

    @Column(nullable = false)
    
    private String moneda;
   
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    
    private String emailUsuario;
    
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)

    private String contrasena;

    @Column
    private boolean logueado;

    @Override
    public boolean pagar(BigDecimal monto) {
        // Verificar que el usuario esté autenticado
        if (!logueado) {
            return false;
        }
    
        // Comparar el saldo con el monto a pagar
        if (saldo.compareTo(monto) >= 0) {
            // Actualizar el saldo si el pago es exitoso
            saldo = saldo.subtract(monto);
            System.out.println("Pago realizado con éxito. Saldo restante: " + saldo);
            return true;
        } else {
            System.out.println("Saldo insuficiente. No se puede realizar el pago.");
            return false;
        }
    }
    
    @Override
    public void obtenerDetalles() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setEstrategiaPrecio'");
    }



}
