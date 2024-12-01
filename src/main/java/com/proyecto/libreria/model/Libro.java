package com.proyecto.libreria.model;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Asegura que el autor se guarde automáticamente
    @JoinColumn(name = "autor_id")
    //@Transient
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String idioma;
    private Long numeroDeDescargas;

    public Libro() {
    }

    public Libro(Libro libro) {
    }

    public Libro(DatosLibro datosLibro) {
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            this.autor = new Autor(datosLibro.autor().get(0)); // Toma el primer autor de la lista
        } else {
            this.autor = null;
        }
        this.categoria =  categoriaModificado(datosLibro.categoria());
        this.idioma = idiomaModificado(datosLibro.idioma());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    private Categoria categoriaModificado(List<String> generos) {
        if (generos == null || generos.isEmpty()) {
            return Categoria.DESCONOCIDO;
        }
        Optional<String> firstGenero = generos.stream()
                .map(g -> {
                    int index = g.indexOf("--");
                    return index != -1 ? g.substring(index + 2).trim() : null;
                })
                .filter(Objects::nonNull)
                .findFirst();
        return firstGenero.map(Categoria::fromString).orElse(Categoria.DESCONOCIDO);
    }

    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";
        }
        return idiomas.get(0);
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

    public Autor getAutores() {
        return autor;
    }

    public void setAutores(Autor autores) {
        this.autor = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "  \nid=" + id +
                        "  \nLibro Id:" + id +
                        ", \nTítulo:'" + titulo + '\'' +
                        ", \nAutor:" + (autor != null ? autor.getNombre() : "N/A")+
                        ", \nCategoría:" + categoria +
                        ", \nIdioma:" + idioma +
                        ", \nCantidad de descargas:" + numeroDeDescargas;
    }
}