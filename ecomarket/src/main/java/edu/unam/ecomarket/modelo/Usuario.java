package edu.unam.ecomarket.modelo;

public class Usuario {
 private Long idUsuario;
 private String nombre;
 private String contrasena;
 private String email;
 
public Usuario(String nombre, String contrasena, String email) {
    this.nombre = nombre;
    this.contrasena = contrasena;
    this.email = email;
}

public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public String getContrasena() {
    return contrasena;
}
public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}



 
}
