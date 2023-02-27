package com.besysoft.ejerciciounidad5.services.interfaces;

import com.besysoft.ejerciciounidad5.domain.entity.Genero;
import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;

import java.util.List;

public interface GeneroService {

    Genero save(Genero genero);

    List<Genero> findAll();

    List<Pelicula> findPeliculasByGeneroNombre(String nombre);

    Genero findById(Long id);

    Genero update(Long id, Genero genero);
}
