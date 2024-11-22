package edu.unam.ecomarket.modelo;

public class ProductoIndividual implements Producto {
 private Long idProducto;
 private String nombre;
 private String descripcion;
 private Long cantidad;
 private CategoriaProducto categoria;
 private TipoUnidad unidad;

 public ProductoIndividual(String nombre, String descripcion, Long cantidad, CategoriaProducto categoria,
        TipoUnidad unidad) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.cantidad = cantidad;
    this.categoria = categoria;
    this.unidad = unidad;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public String getDescripcion() {
    return descripcion;
}
public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}
public Long getCantidad() {
    return cantidad;
}

public CategoriaProducto getCategoria() {
    return categoria;
}
public void setCategoria(CategoriaProducto categoria) {
    this.categoria = categoria;
}
public TipoUnidad getUnidad() {
    return unidad;
}
public void setUnidad(TipoUnidad unidad) {
    this.unidad = unidad;
}

public void actualizarInventario(Long cantidad){
    this.cantidad = cantidad;
}

@Override
public void aplicarDescuento() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'aplicarDescuento'");
}
@Override
public void esDisponible() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'esDisponible'");
}
@Override
public void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setEstrategiaPrecio'");
}
@Override
public double calcularPrecioFinal(double precioBase) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calcularPrecioFinal'");
}


}
