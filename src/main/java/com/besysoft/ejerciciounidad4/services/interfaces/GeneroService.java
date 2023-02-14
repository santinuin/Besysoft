package com.besysoft.ejerciciounidad4.services.interfaces;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;

import java.util.List;

public interface GeneroService {

    Genero save(Genero genero);

    List<Genero> findAll();

    List<Pelicula> findPeliculasByGeneroNombre(String nombre);

    Genero findById(Long id);

}
