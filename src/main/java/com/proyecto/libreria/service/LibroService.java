package com.proyecto.libreria.service;

import com.proyecto.libreria.dto.LibroDTO;
import com.proyecto.libreria.model.Libro;
import com.proyecto.libreria.repository.LibroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @PersistenceContext
    private EntityManager entityManager;

    // Método para buscar libros por título ignorando mayúsculas y minúsculas
    public List<LibroDTO> buscarLibroPorTitulo(String titulo) {
        // Crear consulta para buscar libros sin importar mayúsculas y minúsculas
        Query query = entityManager.createQuery(
                "SELECT l FROM Libro l WHERE UPPER(l.titulo) LIKE CONCAT('%', UPPER(:titulo), '%')"
        );
        query.setParameter("titulo", titulo);

        // Ejecuta la consulta y obtiene los resultados
        List<Libro> libros = query.getResultList();

        if (libros.isEmpty()) {
            System.out.println("El libro no ha sido encontrado en nuestra base de datos.");
        }

        // Convierte los resultados en una lista de DTOs y los devuelve
        return convierteLibrosADTO(libros);
    }

    @Autowired
    private ConvierteDatos convierteDatos;

    // Método para obtener todos los libros y convertirlos a DTOs
    public List<LibroDTO> obtenerTodosLosLibros() {
        List<Libro> libros = entityManager.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
        return convierteLibrosADTO(libros);
    }

    // Método para convertir lista de libros a DTOs
    private List<LibroDTO> convierteLibrosADTO(List<Libro> libros) {
        return libros.stream()
                .map(libro -> new LibroDTO(libro.getId(), libro.getTitulo(), libro.getNumeroDeDescargas()))
                .collect(Collectors.toList());
    }

    // Método para obtener un libro por ID y convertirlo a DTO
    public LibroDTO obtenerLibroPorId(Long id) {
        Libro libro = entityManager.find(Libro.class, id);
        return libro != null ? convertirADTO(libro) : null;
    }

    // Método auxiliar para convertir un libro a DTO
    private LibroDTO convertirADTO(Libro libro) {
        return new LibroDTO(libro.getId(), libro.getTitulo(), libro.getNumeroDeDescargas());
    }
}
