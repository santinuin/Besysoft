package com.besysoft.ejerciciounidad6.dto;

import com.besysoft.ejerciciounidad6.domain.entity.Personaje;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PeliculaDTO {

    private long id;

    @NotNull(message = "Nombre no puede ser nulo")
    @NotEmpty(message = "Nombre no puede estar vacio")
    @NotBlank(message = "Nombre no puede estar vacio")
    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaDeCreacion;

    private Integer calificacion;

    private List<Personaje> personajes;

    public PeliculaDTO() {
    }

    public PeliculaDTO(long id, String titulo, Date fechaDeCreacion, Integer calificacion, List<Personaje> personajes) {
        this.id = id;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeliculaDTO that = (PeliculaDTO) o;

        if (id != that.id) return false;
        if (!Objects.equals(titulo, that.titulo)) return false;
        return Objects.equals(calificacion, that.calificacion);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (fechaDeCreacion != null ? fechaDeCreacion.hashCode() : 0);
        result = 31 * result + (calificacion != null ? calificacion.hashCode() : 0);
        return result;
    }
}
