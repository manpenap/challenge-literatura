package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :anio AND (a.anoMuerte IS NULL OR a.anoMuerte >= :anio)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") Integer anio);


}
