package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class PagoConTarjeta implements MetodoPago {
    private Long idTransaccion;
    private String numeroTarjeta;
    private String nombreTitular;
    private Date fechaExpiracion;
    private String cvv;
    private BigDecimal monto;
    private String moneda;
    
    
    public PagoConTarjeta(String numeroTarjeta, String nombreTitular, Date fechaExpiracion, String cvv,
            BigDecimal monto, String moneda) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.monto = monto;
        this.moneda = moneda;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    @Override
    public boolean autenticar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
    }
    @Override
    public boolean procesarPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procesarPago'");
    }
    @Override
    public boolean cancelarPago() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelarPago'");
    }

    
}
