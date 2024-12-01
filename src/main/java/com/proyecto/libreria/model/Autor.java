package com.proyecto.libreria.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.libreria.dto.AutorDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    private Integer fechaDeNacimiento;

    @JsonProperty("death_year")
    private Integer fechaDeDefuncion;

    public Autor() {
    }
    public Autor(Autor autor) {
    }

    @JsonCreator
    public Autor(
            @JsonProperty("name") String nombre,
            @JsonProperty("birth_year") Integer fechaDeNacimiento,
            @JsonProperty("death_year") Integer fechaDeDefuncion) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    // Constructor que recibe datos de la API
    public Autor(AutorDTO authorDTO) {
        this.nombre = authorDTO.nombre();
        this.fechaDeNacimiento = authorDTO.fechaDeNacimiento();
        this.fechaDeDefuncion = authorDTO.fechaDeDefuncion();
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

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(Integer fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "Id del autor" + id +
                ", Nombre:'" + nombre + '\'' +
                ", Fecha de Nacimiento:" + fechaDeNacimiento +
                ", Fecha de Defunción:" + fechaDeDefuncion +
                '}';
    }
}
