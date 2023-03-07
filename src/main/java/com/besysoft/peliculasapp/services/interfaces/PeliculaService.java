package com.besysoft.peliculasapp.services.interfaces;

import com.besysoft.peliculasapp.domain.entity.Pelicula;
import com.besysoft.peliculasapp.dto.PeliculaDTO;
import com.besysoft.peliculasapp.exceptions.IdNotFoundException;

import java.util.Date;
import java.util.List;

public interface PeliculaService {

    Pelicula save(PeliculaDTO pelicula);

    List<PeliculaDTO> findAll();

    List<PeliculaDTO> findByTitulo(String titulo);

    List<PeliculaDTO> findByDateBetween(Date desde, Date hasta);

    List<PeliculaDTO> findByCalificacionBetween(Integer desde, Integer hasta);

    PeliculaDTO findById(Long id) throws IdNotFoundException;;

    Pelicula update(Long id, PeliculaDTO pelicula) throws IdNotFoundException;

}
