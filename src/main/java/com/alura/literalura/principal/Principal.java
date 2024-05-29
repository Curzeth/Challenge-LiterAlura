package com.alura.literalura.principal;

import com.alura.literalura.repository.ILibroRepositorio;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private ILibroRepositorio repositorio;
    private final String URL_BASE = "https://gutendex.com/books/";

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

//            switch (opcion) {
//                case 1:
//                    getLibroData;
//                    break;
//            }
        }
    }
}

