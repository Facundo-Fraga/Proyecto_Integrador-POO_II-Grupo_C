package edu.unam.ecomarket.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private Long idPedido;
    private LocalDateTime fecha;
    // a un pedido le corresponde un metodo de pago
    private MetodoPago metodoPago;
    private MetodoEnvio metodoEnvio;
    private ArrayList<DetallePedido> listaDetallesPedido;
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    
    public Pedido(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    

}
