package Modelo;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private String editorial;
    private EnumGenero genero;
    private int totalCopias;
    private int copiasDisponibles;
    private EnumEstado estado;

    public Libro(String isbn, String titulo, String autor, int añoPublicacion, String editorial, EnumGenero genero, int totalCopias) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.editorial = editorial;
        this.genero = genero;
        this.totalCopias = totalCopias;
        this.copiasDisponibles = totalCopias;
        this.estado = EnumEstado.DISPONIBLE;
    }
}
