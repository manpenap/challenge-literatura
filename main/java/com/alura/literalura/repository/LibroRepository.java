package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT DISTINCT lang FROM Libro l JOIN l.lenguajes lang")
    List<String> findDistinctIdiomas();

    @Query("SELECT l FROM Libro l JOIN l.lenguajes lang WHERE lang = :idioma")
    List<Libro> findByIdioma(@Param("idioma") String idioma);

    @Query("SELECT COUNT(l) FROM Libro l JOIN l.lenguajes lang WHERE lang = 'en'")
    long contarLibrosEnIngles();

    @Query("SELECT COUNT(l) FROM Libro l JOIN l.lenguajes lang WHERE lang = 'es'")
    long contarLibrosEnEspanol();

}
