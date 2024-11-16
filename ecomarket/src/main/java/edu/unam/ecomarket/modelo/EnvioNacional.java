package edu.unam.ecomarket.modelo;

public class EnvioNacional implements MetodoEnvio {
    private Long nroSeguimiento;
    private String provinciaDestino;
    private double tarifaPorKm;
    private double distancia;
    
    

    public EnvioNacional(String provinciaDestino, double tarifaPorKm, double distancia) {
        this.provinciaDestino = provinciaDestino;
        this.tarifaPorKm = tarifaPorKm;
        this.distancia = distancia;
    }
    public String getProvinciaDestino() {
        return provinciaDestino;
    }
    public void setProvinciaDestino(String provinciaDestino) {
        this.provinciaDestino = provinciaDestino;
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
