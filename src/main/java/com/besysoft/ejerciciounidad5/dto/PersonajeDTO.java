package com.besysoft.ejerciciounidad5.dto;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;

public class PersonajeDTO {

    private Long id;

    private String nombre;

    private Integer edad;

    private Integer peso;

    private String historia;

    private Pelicula pelicula;

    public PersonajeDTO() {
    }

    public PersonajeDTO(Long id, String nombre, Integer edad, Integer peso, String historia, Pelicula pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.pelicula = pelicula;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
