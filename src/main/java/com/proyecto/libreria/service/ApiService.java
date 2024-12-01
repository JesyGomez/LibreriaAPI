package com.proyecto.libreria.service;

import com.proyecto.libreria.model.Autor;
import com.proyecto.libreria.model.Datos;
import com.proyecto.libreria.model.DatosLibro;
import com.proyecto.libreria.model.Libro;
import com.proyecto.libreria.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ObjectMapper objectMapper;

    public void guardarLibrosDesdeApi(String url) {
        // Paso 1: Obtener los datos en formato JSON desde la API
        String json = consumoAPI.obtenerDatos(url);

        // Paso 2: Convertir el JSON a objetos de tipo Datos (que contiene la lista de libros)
        try {
            Datos datos = objectMapper.readValue(json, Datos.class);  // Deserializa el JSON en el objeto Datos
            List<DatosLibro> libros = datos.resultados();  // Obtener la lista de libros

            // Paso 3: Guardar cada libro en la base de datos
            for (DatosLibro datosLibro : libros) {
                // Convertir cada DatosLibro a un Libro antes de guardar
                Libro libro = new Libro();
                libro.setId(datosLibro.id());
                libro.setTitulo(datosLibro.titulo());
                libro.setAutor((Autor) datosLibro.autor());
                libro.setIdioma(String.valueOf(datosLibro.idioma()));
                libro.setNumeroDeDescargas(datosLibro.numeroDeDescargas());

                libroRepository.save(libro);  // Guardamos el libro en la base de datos
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al procesar los datos de la API");
        }
    }
}
