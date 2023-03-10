package com.besysoft.peliculasapp.dto;

import com.besysoft.peliculasapp.domain.entity.Pelicula;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class GeneroDTO {

    private Long id;

    @NotNull(message = "Nombre no puede ser nulo")
    @NotEmpty(message = "Nombre no puede estar vacio")
    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;

    private List<Pelicula> peliculas;

    public GeneroDTO() {
    }

    public GeneroDTO(Long id, String nombre, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = peliculas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneroDTO generoDTO = (GeneroDTO) o;

        if (!Objects.equals(id, generoDTO.id)) return false;
        return Objects.equals(nombre, generoDTO.nombre);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
