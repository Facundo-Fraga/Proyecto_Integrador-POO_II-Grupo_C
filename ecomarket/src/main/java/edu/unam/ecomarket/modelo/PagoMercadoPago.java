package edu.unam.ecomarket.modelo;

import java.math.BigDecimal;

import org.w3c.dom.events.MouseEvent;

public class PagoMercadoPago implements MetodoPago {
    private Long idTransaccion;
    private String emailUsuario;
    private String tokenAcceso;
    private BigDecimal monto;
    private String moneda;

    public PagoMercadoPago(String emailUsuario, String tokenAcceso, BigDecimal monto, String moneda) {
        this.emailUsuario = emailUsuario;
        this.tokenAcceso = tokenAcceso;
        this.monto = monto;
        this.moneda = moneda;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
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
