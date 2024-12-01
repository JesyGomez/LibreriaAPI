package com.proyecto.libreria.model;

import java.util.List;

public enum Categoria {
    ACCION("Action"),
    ROMANCE("Romance"),
    CRIMEN("Crime"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    AVENTURA("Adventure"),
    FICCION("Fiction"),
    DESCONOCIDO("Desconocido");

    private String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public static Categoria fromString(String text) {
        if (text == null) {
            return Categoria.DESCONOCIDO;
        }
        for (Categoria categoriaEnum : Categoria.values()) {
            if (categoriaEnum.categoria.equalsIgnoreCase(text)) { // Ignorar mayúsculas/minúsculas
                return categoriaEnum;
            }
        }
        return Categoria.DESCONOCIDO;
    }

    public static Categoria fromString(List<String> categorias) {
        if (categorias == null || categorias.isEmpty()) {
            return Categoria.DESCONOCIDO;
        }
        for (String categoria : categorias) {
            Categoria encontrada = fromString(categoria);
            if (encontrada != Categoria.DESCONOCIDO) {
                return encontrada;
            }
        }
        return Categoria.DESCONOCIDO;
    }
}
