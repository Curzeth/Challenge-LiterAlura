package com.alura.literalura.repository;

import com.alura.literalura.model.Idiomas;
import com.alura.literalura.model.InformacionDeAutor;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepositorio extends JpaRepository<Libro, Long> {
    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<InformacionDeAutor> getInformacionDeAutor();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE birthYear > :date")
    List<InformacionDeAutor> getAuthorLiveAfter (Integer date);

    List<Libro> encontrarPorIdioma (Idiomas idiomas);
}