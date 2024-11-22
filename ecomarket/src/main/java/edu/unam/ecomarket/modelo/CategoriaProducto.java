package edu.unam.ecomarket.modelo;

public enum CategoriaProducto {
    ALIMENTOS_ORGÁNICOS,
    ROPA_SOSTENIBLE,
    COSMÉTICOS_ECOLOGICOS,
    HIGIENE_PERSONAL,
    ENERGÍA_RENOVABLE,
    MATERIALES_DE_CONSTRUCCIÓN_SOSTENIBLES,
    PRODUCTOS_DE_LIMPIEZA_ECOLOGICOS,
    TRANSPORTE_ECOLOGICO,
    JUGUETES_SOSTENIBLES,
    ARTÍCULOS_DE_OFICINA_ECOLOGICOS;

    @Override
    public String toString() {
        // Este método es opcional, para personalizar la forma en que se muestra el nombre de la categoría
        return name().replace("_", " ").toLowerCase();
    }
}
