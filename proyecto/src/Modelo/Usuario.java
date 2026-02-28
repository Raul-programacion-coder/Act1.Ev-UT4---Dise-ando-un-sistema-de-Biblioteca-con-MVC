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


}
