package edu.unam.ecomarket.modelo;

import java.util.ArrayList;

public class Cliente extends Usuario {
    
    private ArrayList<Pedido> pedidos;

    public Cliente(String nombre, String contrasena, String email) {
        super(nombre, contrasena, email);  
    }
}
