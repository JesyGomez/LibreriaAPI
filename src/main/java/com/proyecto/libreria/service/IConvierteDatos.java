package com.proyecto.libreria.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
   //pasa de formato json a java

}
