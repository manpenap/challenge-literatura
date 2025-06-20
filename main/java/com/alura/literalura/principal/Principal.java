package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;
    private Scanner teclado = new Scanner(System.in);
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository repository, AutorRepository autorRepository){
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while (opcion !=0){
            var menu = """
                    1- Buscar libro por título
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibroRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    mostrarEstadisticasPorIdioma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Debe escoger una opción válida");
                    break;

            }
        }


    }

    private void mostrarEstadisticasPorIdioma() {
        long librosEnIngles = repositorio.contarLibrosEnIngles();
        long librosEnEspanol = repositorio.contarLibrosEnEspanol();

        System.out.println("📊 Estadísticas de libros por idioma:");
        System.out.println("Libros en inglés (en): " + librosEnIngles);
        System.out.println("Libros en español (es): " + librosEnEspanol);
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Escribe el año en que quieres conocer a los autores vivos");
        var anio = teclado.nextInt();
        autores = autorRepository.findAutoresVivosEnAnio(anio);

        if (autores.isEmpty()){
            System.out.println("No tenemos registro de autores registrados en ese año");
        } else{
            autores.forEach(System.out::println);
        }


    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();

        autores.forEach(System.out::println);
    }

    private void listarLibroRegistrados() {
        libros = repositorio.findAll();

        libros.forEach(System.out::println);
    }

    private DatosLibro getDatosSerie() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var url = URL_BASE + nombreLibro.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(url);

        System.out.println("URL: " + url);
        System.out.println("Este es el json: " + json); // log

        if (json == null || json.isBlank()) {
            System.out.println("No se pudo obtener datos del libro. La respuesta fue vacía.");
            return null;
        }

        var datosLibros = convierteDatos.obtenerDatos(json, DatosLibros.class);
        if (datosLibros.resultados().isEmpty()) {
            System.out.println("No se encontraron resultados para ese título.");
            return null;
        }

        return datosLibros.resultados().get(0);
    }

    private void buscarLibro() {
        DatosLibro datos = getDatosSerie();
        if (datos == null) return;

        // Buscar o guardar los autores antes de asignarlos al libro
        List<Autor> autores = datos.autor().stream()
                .map(datoAutor -> autorRepository
                        .findByNombre(datoAutor.autor())
                        .orElseGet(() -> autorRepository.save(new Autor(datoAutor)))
                )
                .toList();

        Libro libro = new Libro(datos);
        libro.setAutores(autores);  // asignar autores ya guardados
        repositorio.save(libro);

        System.out.println("Libro guardado: " + datos.titulo());
    }


}
