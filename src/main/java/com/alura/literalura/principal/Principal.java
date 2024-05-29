package com.alura.literalura.principal;

import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void mostrarMenu() {
        var option = - 1;
        while (option != 0) {
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
            option = sc.nextInt();
            sc.nextLine();
        }
    }
}

