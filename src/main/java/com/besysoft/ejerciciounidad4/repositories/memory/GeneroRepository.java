package com.besysoft.ejerciciounidad4.repositories.memory;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;

import java.util.List;

public interface GeneroRepository {

    List<Genero> findAll();

    Genero save(Genero genero);

    List<Pelicula> findByGenero(String nombre);

    Genero updateGenero(Long id, Genero genero);

}
