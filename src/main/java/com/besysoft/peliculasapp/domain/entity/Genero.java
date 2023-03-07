package com.besysoft.peliculasapp.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 15)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id")
    //@JsonIgnoreProperties(value = "id")
    private List<Pelicula> peliculas;

    public Genero() {
    }

    public Genero(Long id, String nombre, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = peliculas;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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
