package com.besysoft.peliculasapp.dto;

import com.besysoft.peliculasapp.domain.entity.Pelicula;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PersonajeDTO {

    private Long id;

    @NotNull(message = "Nombre no puede ser nulo")
    @NotEmpty(message = "Nombre no puede estar vacio")
    @NotBlank(message = "Nombre no puede estar vacio")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonajeDTO that = (PersonajeDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(nombre, that.nombre)) return false;
        if (!Objects.equals(edad, that.edad)) return false;
        if (!Objects.equals(peso, that.peso)) return false;
        return Objects.equals(historia, that.historia);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (edad != null ? edad.hashCode() : 0);
        result = 31 * result + (peso != null ? peso.hashCode() : 0);
        result = 31 * result + (historia != null ? historia.hashCode() : 0);
        return result;
    }
}
