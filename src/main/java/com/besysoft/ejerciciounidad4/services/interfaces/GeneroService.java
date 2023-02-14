package com.besysoft.ejerciciounidad4.services.interfaces;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;

import java.util.List;

public interface GeneroService {

    Genero save(Genero genero);

    Genero update(Genero genero);

    List<Genero> findAll();

    List<Pelicula> findByGenero(String nombre);

    Genero updateGenero(Long id, Genero genero);
}
