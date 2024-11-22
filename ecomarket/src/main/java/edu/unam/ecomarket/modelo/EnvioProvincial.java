package edu.unam.ecomarket.modelo;

public class EnvioProvincial extends MetodoEnvio {
    
    @Override
    public void calcularCostoBase() {
        this.tarifaInterna = 14000;    
    }

    @Override
    public void seleccionarTipoEnvio() {
        this.tipo = TipoEnvio.PROVINCIAL;    
    }
    


}
