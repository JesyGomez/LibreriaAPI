package com.proyecto.libreria.repository;

import com.proyecto.libreria.model.Autor;
import com.proyecto.libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findAll(); // Ya proporcionado por JpaRepository
    Libro findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdioma(String idioma);

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    Autor findAutorByNombre(@Param("nombre") String nombre);

    @Transactional
    @Modifying
    @Query("INSERT INTO Autor (nombre) VALUES (:nombre)")
    Autor saveAutor(@Param("nombre") String nombre);
}
