package edu.unam.ecomarket.modelo;

public class EnvioNacional extends MetodoEnvio {
   /* 
    @Override
    public void calcularCostoBase() {
        this.tarifaInterna = 20000;    
    }
    */
    @Override
    public void seleccionarTipoEnvio() {
        this.tipo = TipoEnvio.NACIONAL;    
    }
    
    
}
