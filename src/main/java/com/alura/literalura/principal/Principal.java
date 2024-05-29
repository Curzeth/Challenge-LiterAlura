package com.alura.literalura.principal;

import com.alura.literalura.model.Datos;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.ILibroRepositorio;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private ILibroRepositorio repositorio;
    private final String URL_BASE = "https://gutendex.com/books/";
    private List<Libro> libros;
    private String libroSeleccionado;

    public Principal (ILibroRepositorio repositorio){
        this.repositorio = repositorio;
    }

    public void mostrarMenu() {
        var opcion = - 1;
        while (opcion != 0) {
            var menu = """
                ----------------------------------------------------
                Bienvenido/a a LiterAlura.
                ----------------------------------------------------
                1) Buscar libro por titulo
                2) Listar libros registrados
                3) Listar autores registrados
                4) Listar autores vivos por año
                5) Listar libros por idioma
                0) Salir
                ----------------------------------------------------
                """;
            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    obtenerDatosLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
            }
        }
    }

        private String obtenerDatosDeUsuario() {
            System.out.println("Escriba el titulo del libro que desea buscar");
            libroSeleccionado = sc.nextLine();
            return libroSeleccionado;
        }

        private Datos obtenerDatosLibroDeAPI(String libroTitulo){
            var json = consumoAPI.obtenerDatos(URL_BASE + "?search=%20" + libroTitulo.replace(" ", "+"));
            var datos = conversor.obtenerDatos(json, Datos.class);
            return datos;
        }

        private Optional<Libro> obtenerInformacionLibro(Datos datosLibro, String libroTitulo) {
            Optional<Libro> libros = datosLibro.resultados().stream()
                    .filter(l -> l.titulo().toLowerCase().contains(libroTitulo.toLowerCase()))
                    .map(b -> new Libro(b.titulo(), b.idiomas(), b.descargas(), b.autores()))
                    .findFirst();
            return libros;
        }

        private Optional<Libro> obtenerDatosLibro() {
            String libroTitulo = obtenerDatosDeUsuario();
            Datos informacionLibro = obtenerDatosLibroDeAPI(libroTitulo);
            Optional<Libro> libro = obtenerInformacionLibro(informacionLibro, libroTitulo);

            if (libro.isPresent()) {
                var b = libro.get();
                repositorio.save(b);
            } else {
                System.out.println("Libro no encontrado");
            }
            return libro;
        }

        private void listarLibrosRegistrados(){
            libros = repositorio.findAll();

            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }
}