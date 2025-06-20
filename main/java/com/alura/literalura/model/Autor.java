package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;

    private Integer anoNacimiento;
    private Integer anoMuerte;
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor(){};

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.autor();
        this.anoNacimiento = datosAutor.nacimiento();
        this.anoMuerte = datosAutor.muerte();
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", año de Nacimiento= " + anoNacimiento +
                ", año de Muerte= " + anoMuerte +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoMuerte() {
        return anoMuerte;
    }

    public void setAnoMuerte(Integer anoMuerte) {
        this.anoMuerte = anoMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
