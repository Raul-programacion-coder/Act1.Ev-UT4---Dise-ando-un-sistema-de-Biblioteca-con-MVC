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

    public boolean hayCopiasDisponibles() {
        return copiasDisponibles > 0;
    }

    public void prestar() {
        if (!hayCopiasDisponibles())
            throw new IllegalStateException("No hay copias disponibles");

        copiasDisponibles--;
        if (copiasDisponibles == 0)
            estado = EnumEstado.PRESTADO;
    }

    public void devolver() {
        if (copiasDisponibles < totalCopias) {
            copiasDisponibles++;
            estado = EnumEstado.DISPONIBLE;
        }
    }

    public void reservar() {
        if (estado == EnumEstado.DISPONIBLE)
            throw new IllegalStateException("Libro disponible, no se puede reservar");
        estado = EnumEstado.RESERVADO;
    }

    public void cancelarReserva() {
        if (estado == EnumEstado.RESERVADO)
            estado = EnumEstado.DISPONIBLE;
    }

    //getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public EnumGenero getGenero() {
        return genero;
    }

    public EnumEstado getEstado() {
        return estado;
    }
}
