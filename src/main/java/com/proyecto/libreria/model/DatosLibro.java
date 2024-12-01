package com.proyecto.libreria.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") Long id,  // Ajustado el nombre de la propiedad a minúsculas
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autor,
        @JsonAlias("subjects") List<String> categoria,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Long numeroDeDescargas
) {
}
