# Librería - Catálogo de Libros

Bienvenido a **Librería**, un catálogo de libros interactivo desarrollado en Java. Este proyecto permite a los usuarios explorar una base de datos de libros a través de una interfaz de consola, consultando información obtenida de una API externa de libros.

## Descripción

Con esta aplicación podrás:

- Realizar solicitudes HTTP para consumir una API de libros.
- Manipular datos en formato JSON.
- Guardar y consultar datos en una base de datos.
- Filtrar y mostrar información de interés sobre libros y autores.

## Objetivo de este Proyecto:

Crear un **Catálogo de Libros** que permita la interacción del usuario mediante texto (consola). El sistema consta con al menos cinco opciones de interacción, y todos los libros y autores provienen de una API específica.

## Funcionalidades Principales

El catálogo de libros **Librería** incluye las siguientes funcionalidades:

1. **Buscar Libros por Autor**: Permite al usuario buscar todos los libros escritos por un autor específico.
2. **Listar Todos los Libros**: Muestra todos los libros disponibles en la base de datos.
3. **Filtrar por Género**: Permite al usuario ver una lista de libros de un género determinado.
4. **Consultar Detalles de un Libro**: Proporciona detalles de un libro específico, como el título, autor, año de publicación, y sinopsis.
5. **Agregar Libros a Favoritos**: Los usuarios pueden marcar libros como favoritos para acceder a ellos rápidamente en futuras consultas.

## Requisitos del Sistema

Para ejecutar el proyecto en tu máquina local, asegúrate de tener:

- **Java JDK** (Yo utilicé la versión 21)
- **IDE** recomendado: IntelliJ
- **Dependencias**:
    - Conexión a una base de datos (ejemplo: Postman y Postgred)
    - Librerías de consumo de API (puedes usar **HttpClient** o **OkHttp**)
    - Librerías de manipulación de JSON, como **Gson** o **Jackson**

## Configuración del Ambiente

Sigue estos pasos para configurar el ambiente de desarrollo y comenzar a trabajar en el proyecto:

1. **Instala Java JDK** (si no lo tienes ya instalado).
2. **Configura la base de datos**:
    - Crear una base de datos para almacenar los datos de los libros.
3. **Clona el repositorio** y abre el proyecto en tu IDE preferido.
4. **Configura las dependencias** necesarias en el archivo `pom.xml` si usas Maven, o en el archivo de configuración de tu preferencia.

## Guía de Instalación

1. Clona este repositorio:

    ```bash
    git clone https://github.com/JesyGomez/LibreriaAPI.git
    ```

2. Ejecuta el archivo principal del proyecto en tu IDE para iniciar la aplicación.

## Estructura del Proyecto

- **src/principal**: Contiene el código fuente principal del proyecto.
    - `Principal.java`: Clase principal donde se ejecuta la aplicación.
    - `Service.java`: Clase para el consumo de la API.
    - `Model.java`: Modelo que representa los libros.
    - `LibroRepository.java`: Clase de conexión y consultas a la base de datos.
- **resources**: Contiene archivos de configuración.

## Ejecución

Ejecuta el programa desde el archivo `LibreriaAplicacion.java` y sigue las instrucciones en la consola para interactuar con el catálogo de libros.

## Interacción en Consola

El menú principal te ofrecerá las siguientes opciones de interacción:

1. **Buscar Libros por Autor**
2. **Listar Todos los Libros**
3. **Filtrar por Género**
4. **Consultar Detalles de un Libro**
5. **Agregar Libros a Favoritos**

## Ejemplo de Uso

```bash
Bienvenido a Librería - Catálogo de Libros

1. Buscar Libros por Autor
2. Listar Todos los Libros
3. Filtrar por Género
4. Consultar Detalles de un Libro
5. Agregar Libros a Favoritos
0. Salir

Por favor, elige una opción: 