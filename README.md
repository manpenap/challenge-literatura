# LiterAlura - Catálogo de Libros Interactivo

## Descripción

LiterAlura es una aplicación de consola desarrollada como parte del desafío de programación de Alura. Se trata de un catálogo de libros interactivo que permite a los usuarios buscar libros a través de una API externa (Gutendex), guardar la información en una base de datos PostgreSQL y realizar consultas sobre los libros y autores almacenados.

La aplicación ofrece una interfaz de texto por consola donde los usuarios pueden interactuar con el sistema mediante diversas opciones de consulta y búsqueda.

## Características Principales

- **Búsqueda de libros**: Permite buscar libros por título utilizando la API de Gutendex
- **Almacenamiento en base de datos**: Guarda la información de libros y autores en una base de datos PostgreSQL
- **Consulta de registros**: Muestra los libros y autores que se han guardado en la base de datos
- **Filtros avanzados**: Permite filtrar autores vivos en un año específico
- **Estadísticas por idioma**: Muestra estadísticas sobre los libros registrados según su idioma

## Estructura del Proyecto

El proyecto sigue una estructura basada en Spring Boot con las siguientes capas:

- **Modelo (model)**: Clases de entidad y DTOs para libros y autores
    - `Autor.java`: Entidad que representa un autor
    - `Libro.java`: Entidad que representa un libro
    - `DatosAutor.java`: Record para manejar los datos de autores desde la API
    - `DatosLibro.java`: Record para manejar los datos de libros desde la API
    - `DatosLibros.java`: Record que encapsula la lista de resultados de la API

- **Repositorios (repository)**: Interfaces para acceder a la base de datos
    - `AutorRepository.java`: Repositorio para operaciones CRUD de autores
    - `LibroRepository.java`: Repositorio para operaciones CRUD de libros

- **Servicios (service)**: Clases para lógica de negocio
    - `ConsumoAPI.java`: Servicio para realizar peticiones HTTP a la API externa
    - `ConvierteDatos.java`: Servicio para convertir JSON a objetos Java
    - `IConvierteDatos.java`: Interfaz para la conversión de datos

- **Capa principal**:
    - `Principal.java`: Clase que contiene la lógica de interacción con el usuario mediante la consola
    - `LiteraluraApplication.java`: Clase principal que inicia la aplicación Spring Boot

## Requisitos

- Java 17 o superior
- Maven
- PostgreSQL
- Variables de entorno configuradas para la conexión a la base de datos:
    - `DB_HOST`: Host de la base de datos PostgreSQL
    - `DB_NAME`: Nombre de la base de datos
    - `DB_USER`: Usuario de la base de datos
    - `DB_PASSWORD`: Contraseña del usuario de la base de datos

## Instalación y Ejecución

1. **Clonar el repositorio**:
   ```bash
   git clone <url-del-repositorio>
   cd literalura
   ```

2. **Configurar las variables de entorno**:
   ```bash
   export DB_HOST=localhost:5432
   export DB_NAME=literalura
   export DB_USER=tu_usuario
   export DB_PASSWORD=tu_contraseña
   ```

3. **Compilar el proyecto**:
   ```bash
   ./mvnw clean package
   ```

4. **Ejecutar la aplicación**:
   ```bash
   ./mvnw spring-boot:run
   ```

## Uso

Una vez que la aplicación está en ejecución, se mostrará un menú de opciones en la consola:

1. **Buscar libro por título**: Permite buscar un libro en la API y guardarlo en la base de datos
2. **Listar libros registrados**: Muestra todos los libros guardados en la base de datos
3. **Listar autores registrados**: Muestra todos los autores guardados en la base de datos
4. **Listar autores vivos en un determinado año**: Filtra autores que estaban vivos en un año específico
5. **Listar libros por idioma**: Muestra estadísticas de libros por idioma
0. **Salir**: Termina la ejecución de la aplicación

## API Externa

La aplicación utiliza la API de Gutendex para obtener información sobre libros. La URL base utilizada es:
```
https://gutendex.com/books/?search=
```

## Relaciones entre Entidades

- **Libro-Autor**: Relación muchos a muchos. Un libro puede tener varios autores y un autor puede tener varios libros.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para aplicaciones Java
- **Spring Data JPA**: Para acceso a datos y operaciones CRUD
- **Jackson**: Para la serialización y deserialización de JSON
- **PostgreSQL**: Base de datos relacional
- **Java 17**: Lenguaje de programación

## Contribución

Si deseas contribuir a este proyecto, puedes seguir estos pasos:

1. Haz un fork del repositorio
2. Crea una rama para tu característica (`git checkout -b feature/nueva-caracteristica`)
3. Haz commit de tus cambios (`git commit -m 'Añadir nueva característica'`)
4. Haz push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

