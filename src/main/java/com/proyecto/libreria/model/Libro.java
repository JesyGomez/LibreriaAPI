package com.proyecto.libreria.model;

public class Libro {
    private Long id;
    private String nombre;
    private String idioma;

    public Libro(Long id, String nombre, String idioma, Double numeroDeDescargas) {
        this.id = id;
        this.nombre = nombre;
        this.idioma = idioma;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    private Double numeroDeDescargas;

    @Override
    public String toString() {
        return "Libro{" +
                "Nombre del Libro:'" + nombre + '\'' +
                ", Idioma:'" + idioma + '\'' +
                ", Número de Descargas:" + numeroDeDescargas +
                '}';
    }
}
