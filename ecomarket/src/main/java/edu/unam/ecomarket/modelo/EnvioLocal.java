package edu.unam.ecomarket.modelo;

public class EnvioLocal extends MetodoEnvio {
    

    @Override
    public void calcularCostoBase() {
        this.tarifaInterna = 7000;    
    }

    @Override
    public void seleccionarTipoEnvio() {
        this.tipo = TipoEnvio.LOCAL;    
    }
    

}
