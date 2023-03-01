package com.besysoft.ejerciciounidad5.services.interfaces;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.dto.PeliculaDTO;

import java.util.Date;
import java.util.List;

public interface PeliculaService {

    Pelicula save(Pelicula pelicula);

    List<PeliculaDTO> findAll();

    List<PeliculaDTO> findByTitulo(String titulo);

    List<PeliculaDTO> findByDateBetween(Date desde, Date hasta);

    List<PeliculaDTO> findByCalificacionBetween(Integer desde, Integer hasta);

    PeliculaDTO findById(Long id);

    Pelicula update(Long id, Pelicula pelicula);

}
