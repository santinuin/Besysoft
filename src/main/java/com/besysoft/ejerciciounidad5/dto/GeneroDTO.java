package com.besysoft.ejerciciounidad5.dto;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;

import java.util.List;

public class GeneroDTO {

    private Long id;

    private String nombre;

    private List<Pelicula> peliculas;

    public GeneroDTO() {
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

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
