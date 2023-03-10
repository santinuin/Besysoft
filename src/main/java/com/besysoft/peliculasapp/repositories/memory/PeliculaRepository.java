package com.besysoft.peliculasapp.repositories.memory;

import com.besysoft.peliculasapp.domain.entity.Pelicula;

import java.util.Date;
import java.util.List;

public interface PeliculaRepository {

    List<Pelicula> findAll();

    Pelicula save(Pelicula pelicula);

    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByDateBetween(Date desde, Date hasta);

    List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta);

    Pelicula updatePelicula(Long id, Pelicula pelicula);

}
