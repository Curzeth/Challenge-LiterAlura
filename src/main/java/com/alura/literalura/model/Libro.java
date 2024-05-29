package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InformacionDeAutor> autores;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Double descargas;

    public Libro() {
    }

    public Libro(List<InformacionDeLibro> resultados) {
    }

    public Libro(String titulo, List<String> idiomas, Double descargas, List<Autores> autores) {
        this.titulo = titulo;
        this.idiomas = Idiomas.fromString(idiomas.get(0));
        this.descargas = descargas;
        this.autores = new ArrayList<>();
        for (Autores informacionDeAutor : autores){
            InformacionDeAutor autor = new InformacionDeAutor(informacionDeAutor.nombre(), informacionDeAutor.nacimiento(), informacionDeAutor.fallecimiento(), this);
            this.autores.add(autor);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<InformacionDeAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<InformacionDeAutor> autores) {
        autores.forEach(e -> e.setLibro(this));
        this.autores = autores;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return
                "Título: " + titulo + "\n" +
                        "Autores: " + autores + "\n" +
                        "Idiomas: " + idiomas + "\n" +
                        "Descargas: " + descargas + "\n";
    }
}