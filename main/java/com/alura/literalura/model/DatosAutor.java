package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String autor,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer muerte
) {
}
