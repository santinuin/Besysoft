package com.besysoft.ejerciciounidad5.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String nombre;

    @Column(length = 3)
    private Integer edad;

    @Column(length = 3)
    private Integer peso;

    private String historia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pelicula_id")
    //@JsonIgnoreProperties(value = {"id", "personajes"})
    private Pelicula pelicula;

    public Personaje() {
    }

    public Personaje(Long id, String nombre, Integer edad, Integer peso, String historia, Pelicula pelicula) {
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

    public void setId(long id) {
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
