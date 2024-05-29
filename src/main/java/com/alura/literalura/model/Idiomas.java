package com.alura.literalura.model;

public enum Idiomas {
    SPANISH("es"),
    ENGLISH("en"),
    ITALIAN("it"),
    FRENCH("fr"),
    PORTUGUESE("pt");

    private String idiomasSec;

    Idiomas (String idiomasSec) {
        this.idiomasSec = idiomasSec;
    }

    public static Idiomas fromString(String texto) {
        for (Idiomas categoria : Idiomas.values())
            if (categoria.idiomasSec.equalsIgnoreCase(texto)) {
                return categoria;
            }
        throw new IllegalArgumentException("Categoria no encontrada: " + texto);
    }
}