package com.besysoft.ejerciciounidad4.services.interfaces;

import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;

import java.util.Date;
import java.util.List;

public interface PeliculaService {

    Pelicula save(Pelicula pelicula);

    List<Pelicula> findAll();

    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByDateBetween(Date desde, Date hasta);

    List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta);

    Pelicula findById(Long id);
}
