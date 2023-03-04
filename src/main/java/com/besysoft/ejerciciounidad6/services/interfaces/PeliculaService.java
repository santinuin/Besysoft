package com.besysoft.ejerciciounidad6.services.interfaces;

import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad6.dto.PeliculaDTO;

import java.util.Date;
import java.util.List;

public interface PeliculaService {

    Pelicula save(PeliculaDTO pelicula);

    List<PeliculaDTO> findAll();

    List<PeliculaDTO> findByTitulo(String titulo);

    List<PeliculaDTO> findByDateBetween(Date desde, Date hasta);

    List<PeliculaDTO> findByCalificacionBetween(Integer desde, Integer hasta);

    PeliculaDTO findById(Long id);

    Pelicula update(Long id, PeliculaDTO pelicula);

}
