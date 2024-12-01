package com.proyecto.libreria.principal;

import com.proyecto.libreria.model.Categoria;
import com.proyecto.libreria.model.Datos;
import com.proyecto.libreria.model.DatosLibro;
import com.proyecto.libreria.model.Libro;
import com.proyecto.libreria.repository.LibroRepository;
import com.proyecto.libreria.service.ConsumoAPI;
import com.proyecto.libreria.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        try {
            var json = consumoApi.obtenerDatos(URL_BASE);
            var datos = conversor.obtenerDatos(json, Datos.class);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al consumir la API: " + e.getMessage());
        }

        System.out.println("Bienvenido a la Librería LiterAlura");

        var opcion = -1;
        while (opcion != 0) {
            try {
                var menu = """
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1 -> buscarLibroPorTitulo();
                    case 2 -> listarLibrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivosEnDeterminadoAnio();
                    case 5 -> listarLibrosPorIdioma();
                    case 0 -> {
                        System.out.println("Cerrando la aplicación...");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
                teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el título del libro que desea buscar: ");
        String titulo = teclado.nextLine();

        // Construcción de URL segura
        String tituloEncoded = URLEncoder.encode(titulo, StandardCharsets.UTF_8);

        // Consumo de API
        String json = consumoApi.obtenerDatos(URL_BASE + tituloEncoded);
        if (json == null || json.isEmpty()) {
            System.out.println("No se pudo obtener información de la API.");
            return;
        }

        // Conversión de datos
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        if (datos == null || datos.resultados().isEmpty()) {
            System.out.println("No se encontraron libros con el título proporcionado.");
            return;
        }

        // Procesamiento del primer libro encontrado
        DatosLibro datosLibro = datos.resultados().get(0);
        Libro libro = new Libro();
        libro.setId(datosLibro.id());
        libro.setTitulo(datosLibro.titulo());
        libro.setCategoria(Categoria.fromString(datosLibro.categoria()));
        libro.setAutor(datosLibro.autor() != null && !datosLibro.autor().isEmpty() ? datosLibro.autor().get(0) : null);
        libro.setIdioma(datosLibro.idioma() != null && !datosLibro.idioma().isEmpty() ? datosLibro.idioma().get(0) : null);
        libro.setNumeroDeDescargas(datosLibro.numeroDeDescargas());

        System.out.println("Libro encontrado en la API: " + libro);

        // Verificación en el repositorio
        Optional<Libro> libroEnBD = Optional.ofNullable(repositorio.findByTituloContainsIgnoreCase(libro.getTitulo()));
        libroEnBD.ifPresentOrElse(
                l -> System.out.println("El libro ya existe en la base de datos: " + l),
                () -> {
                    repositorio.save(libro);
                    System.out.println("Libro guardado en la base de datos: " + libro);
                }
        );
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            libros.stream()
                    .map(Libro::getAutor)
                    .distinct()
                    .forEach(autor -> System.out.println("Autor: " + autor.getNombre()));
        }
    }

    private void listarAutoresVivosEnDeterminadoAnio() {
        System.out.print("Ingrese el año: ");
        int anio = teclado.nextInt();

        List<Libro> libros = repositorio.findAll();
        libros.stream()
                .map(Libro::getAutor)
                .filter(autor -> {
                    Integer nacimiento = autor.getFechaDeNacimiento();
                    Integer defuncion = autor.getFechaDeDefuncion() != null
                            ? autor.getFechaDeDefuncion()
                            : Integer.MAX_VALUE;
                    return anio >= nacimiento && anio <= defuncion;
                })
                .forEach(autor -> System.out.println("Autor vivo en " + anio + ": " + autor.getNombre()));
    }


    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el idioma: ");
        String idioma = teclado.nextLine();

        List<Libro> libros = repositorio.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}
