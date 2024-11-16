package edu.unam.ecomarket.modelo;

public enum TipoUnidad {
LITRO,
MILILITRO,
KILOGRAMO,
GRAMO;


@Override
    public String toString() {
        // Este método es opcional, para personalizar la forma en que se muestra el nombre de la categoría
        return name().replace("_", " ").toLowerCase();
    }
}
