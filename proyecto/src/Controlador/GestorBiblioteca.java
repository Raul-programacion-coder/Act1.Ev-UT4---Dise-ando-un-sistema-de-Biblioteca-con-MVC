package Controlador;

import Modelo.*;
import excepciones.LibroNoDisponibleException;
import excepciones.PrestamoNoValidoException;

import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {

    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    public void agregarLibro(Libro l) { libros.add(l); }
    public void agregarUsuario(Usuario u) { usuarios.add(u); }


    public void prestarLibro(String isbn, String id)
            throws Exception {
        Libro libro = buscarLibroPorISBN(isbn);
        Usuario usuario = buscarUsuario(id);

        if (libro == null)
            throw new PrestamoNoValidoException("Libro no encontrado");
        if (usuario == null)
            throw new PrestamoNoValidoException("Usuario no encontrado");

        if (!libro.hayCopiasDisponibles())
            throw new LibroNoDisponibleException("No hay copias disponibles");

        if (!usuario.puedePedirLibro(isbn))
            throw new PrestamoNoValidoException("Debe esperar 7 días");

        Prestamo p = new Prestamo(libro, usuario);
        usuario.agregarPrestamo(p);
        prestamos.add(p);
        libro.prestar();
    }


    public void devolverLibro(String isbn, String idUsuario)
            throws PrestamoNoValidoException {
        Prestamo p = buscarPrestamoActivo(isbn, idUsuario);
        if (p == null)
            throw new PrestamoNoValidoException("No existe préstamo");

        p.registrarDevolucion();
        p.getLibro().devolver();
        p.getUsuario().devolverPrestamo(p);
    }

    public void reservarLibro(String isbn)
            throws LibroNoDisponibleException {
        Libro libro = buscarLibroPorISBN(isbn);
        if (libro == null)
            throw new LibroNoDisponibleException("Libro no encontrado");

        if (libro.getEstado() == EnumEstado.DISPONIBLE)
            throw new LibroNoDisponibleException("Libro disponible, no se reserva");

        libro.reservar();
    }

    public void cancelarReservaLibro(String isbn) throws LibroNoDisponibleException {

        Libro libro = buscarLibroPorISBN(isbn);

        if (libro == null)
            throw new LibroNoDisponibleException("Libro no encontrado");

        libro.cancelarReserva();
    }

    public Libro buscarLibroPorISBN(String isbn) {
        for (Libro l : libros)
            if (l.getIsbn().equals(isbn))
                return l;
        return null;
    }

    public List<Libro> buscarLibroPorTitulo(String titulo) {
        List<Libro> res = new ArrayList<>();
        for (Libro l : libros)
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                res.add(l);
        return res;
    }

    public List<Libro> buscarPorGenero(EnumGenero genero) {
        List<Libro> res = new ArrayList<>();
        for (Libro l : libros)
            if (l.getGenero() == genero)
                res.add(l);
        return res;
    }

    public Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios)
            if (u.getId().equals(id))
                return u;
        return null;
    }

    private Prestamo buscarPrestamoActivo(String isbn, String idUsuario) {
        for (Prestamo p : prestamos) {
            if (p.getLibro().getIsbn().equals(isbn) &&
                    p.getUsuario().getId().equals(idUsuario) &&
                    p.getFechaDevolucion() == null)
                return p;
        }
        return null;
    }

    public void listarPrestamosVencidos() {
        for (Prestamo p : prestamos) {
            if (p.estaVencido()) {
                System.out.println("Préstamo vencido: " + p.getLibro().getTitulo() +
                        " -> Usuario: " + p.getUsuario().getNombre());
            }
        }
    }


    public List<Libro> getLibros() {
        return libros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
