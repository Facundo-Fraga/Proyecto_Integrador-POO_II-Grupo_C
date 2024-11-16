package edu.unam.ecomarket.modelo;

public class EnvioProvincial implements MetodoEnvio {
    private Long nroSeguimiento;
    private String ciudadDestino;
    private double tarifaPorKm;
    private double distancia;
    
    
    public EnvioProvincial(String ciudadDestino, double tarifaPorKm, double distancia) {
        this.ciudadDestino = ciudadDestino;
        this.tarifaPorKm = tarifaPorKm;
        this.distancia = distancia;
    }
    public String getCiudadDestino() {
        return ciudadDestino;
    }
    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
    public double getTarifaPorKm() {
        return tarifaPorKm;
    }
    public void setTarifaPorKm(double tarifaPorKm) {
        this.tarifaPorKm = tarifaPorKm;
    }
    public double getDistancia() {
        return distancia;
    }
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    @Override
    public double calcularCostoBase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularCostoBase'");
    }
    @Override
    public double calcularCostoTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularCostoTotal'");
    }


}
