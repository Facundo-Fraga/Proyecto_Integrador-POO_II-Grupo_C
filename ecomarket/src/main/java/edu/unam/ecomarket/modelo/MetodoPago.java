package edu.unam.ecomarket.modelo;

public interface MetodoPago {
    public abstract boolean autenticar();
    public abstract boolean procesarPago();
    public abstract boolean cancelarPago();
}
