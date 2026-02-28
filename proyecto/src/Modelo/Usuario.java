package Modelo;
import excepciones.MaxLibrosPrestadosException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String id;
    private String nombre;
    private List<Prestamo> prestamosActivos = new ArrayList<>();
    private List<Prestamo> historialPrestamos = new ArrayList<>();


    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void agregarPrestamo(Prestamo p) throws MaxLibrosPrestadosException {
        if (prestamosActivos.size() >= 3)
            throw new MaxLibrosPrestadosException("Máximo 3 libros prestados");
        prestamosActivos.add(p);
    }

    public void devolverPrestamo(Prestamo p) {
        prestamosActivos.remove(p);
        historialPrestamos.add(p);
    }

    public boolean puedePedirLibro(String isbn) {
        for (Prestamo p : historialPrestamos) {
            if (p.getLibro().getIsbn().equals(isbn)) {
                if (!p.puedeReprestarse())
                    return false;
            }
        }
        return true;
    }
    

}
