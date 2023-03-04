package com.besysoft.ejerciciounidad6.repositories.memory;

import com.besysoft.ejerciciounidad6.domain.entity.Genero;
import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;

import java.util.List;

public interface GeneroRepository {

    List<Genero> findAll();

    Genero save(Genero genero);

    List<Pelicula> findByGenero(String nombre);

    Genero updateGenero(Long id, Genero genero);

}
