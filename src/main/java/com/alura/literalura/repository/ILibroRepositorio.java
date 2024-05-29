package com.alura.literalura.repository;

import com.alura.literalura.model.Idiomas;
import com.alura.literalura.model.InformacionDeAutor;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepositorio extends JpaRepository<Libro, Long> {
    @Query("SELECT a FROM Libro b JOIN b.autores a")
    List<InformacionDeAutor> getInformacionDeAutor();

    @Query("SELECT a FROM Libro b JOIN b.autores a WHERE a.nacimiento <= :fecha AND (a.fallecimiento >= :fecha OR a.fallecimiento IS NULL)")
    List<InformacionDeAutor> getAutoresVivos (Integer fecha);

    List<Libro> findByIdiomas(Idiomas idiomas);
}