package edu.unam.ecomarket.modelo;

public class EnvioLocal implements MetodoEnvio {
    private Long nroSeguimiento;
    private double distancia;
    private double tarifaInterna;

    public EnvioLocal(double distancia, double tarifaInterna) {
        this.distancia = distancia;
        this.tarifaInterna = tarifaInterna;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTarifaInterna() {
        return tarifaInterna;
    }

    public void setTarifaInterna(double tarifaInterna) {
        this.tarifaInterna = tarifaInterna;
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
