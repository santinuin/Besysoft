package com.besysoft.peliculasapp.repositories.memory;

import com.besysoft.peliculasapp.domain.entity.Genero;
import com.besysoft.peliculasapp.domain.entity.Pelicula;

import java.util.List;

public interface GeneroRepository {

    List<Genero> findAll();

    Genero save(Genero genero);

    List<Pelicula> findByGenero(String nombre);

    Genero updateGenero(Long id, Genero genero);

}
