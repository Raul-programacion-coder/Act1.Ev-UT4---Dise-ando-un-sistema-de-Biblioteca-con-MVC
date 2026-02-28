package App;

import Controlador.GestorBiblioteca;
import Modelo.*;
import Vista.Consola;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        GestorBiblioteca gestor = new GestorBiblioteca();

        Libro l1 = new Libro("ISBN1", "El Quijote", "Cervantes", 1605,
                "Anaya", EnumGenero.NOVELA, 2);
        Libro l2 = new Libro("ISBN2", "Dune", "Frank Herbert", 1965,
                "Ace", EnumGenero.CIENCIA_FICCION, 1);



        Usuario u1 = new Usuario("U1", "Ana");
        Usuario u2 = new Usuario("U2", "Luis");

        gestor.agregarLibro(l1);
        gestor.agregarLibro(l2);
        gestor.agregarUsuario(u1);
        gestor.agregarUsuario(u2);

        gestor.prestarLibro("ISBN1", "U1");
        gestor.prestarLibro("ISBN2", "U2");
        gestor.prestarLibro("ISBN1", "U2");

        Consola.mostrarResumen(gestor);



        System.out.println("\nTras devolución:");
        Consola.mostrarResumen(gestor);

        System.out.println("\nPrueba reservar y cancelar:");
        gestor.reservarLibro("ISBN1");
        Consola.mostrarLibro(l1);
        gestor.cancelarReservaLibro("ISBN1");
        Consola.mostrarLibro(l1);

        System.out.println("\nDevolver libro:");
        gestor.devolverLibro("ISBN1", "U1");
        Consola.mostrarResumen(gestor);

        System.out.println("\nPrueba:");

        List<Libro> encontradosTitulo = gestor.buscarLibroPorTitulo("Dune");
        for (Libro l : encontradosTitulo) {
            Consola.mostrarLibro(l);
        }

        Libro libroISBN = gestor.buscarLibroPorISBN("ISBN1");
        if (libroISBN != null)
            Consola.mostrarLibro(libroISBN);

        List<Libro> novelas = gestor.buscarPorGenero(EnumGenero.CIENCIA_FICCION);
        for (Libro l : novelas) {
            Consola.mostrarLibro(l);
        }


        Consola.mostrarPrestamosVencidos(gestor);
    }
}