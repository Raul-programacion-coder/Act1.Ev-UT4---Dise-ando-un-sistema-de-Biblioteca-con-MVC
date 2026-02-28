package Vista;

import Modelo.*;
import Controlador.GestorBiblioteca;

public class Consola {

    public static void mostrarLibro(Libro l) {
        System.out.println(l.getTitulo() + " - " + l.getEstado());
    }

    public static void mostrarUsuario(Usuario u) {
        System.out.println("Usuario: " + u.getNombre());
        System.out.println("Libros prestados:");
        for (Prestamo p : u.getPrestamosActivos())
            System.out.println(" - " + p.getLibro().getTitulo());
    }

    public static void mostrarResumen(GestorBiblioteca g) {
        System.out.println("=== LIBROS ===");
        for (Libro l : g.getLibros())
            mostrarLibro(l);

        System.out.println("=== USUARIOS ===");
        for (Usuario u : g.getUsuarios())
            mostrarUsuario(u);
    }

    public static void mostrarPrestamosVencidos(GestorBiblioteca gestor) {
        gestor.listarPrestamosVencidos();
    }
}