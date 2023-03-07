package com.besysoft.peliculasapp.services.interfaces;

import com.besysoft.peliculasapp.domain.entity.Genero;
import com.besysoft.peliculasapp.dto.GeneroDTO;
import com.besysoft.peliculasapp.dto.PeliculaDTO;
import com.besysoft.peliculasapp.exceptions.IdNotFoundException;

import java.util.List;

public interface GeneroService {

    Genero save(GeneroDTO genero);

    List<GeneroDTO> findAll();

    List<PeliculaDTO> findPeliculasByGeneroNombre(String nombre);

    GeneroDTO findById(Long id) throws IdNotFoundException;

    Genero update(Long id, GeneroDTO genero) throws IdNotFoundException;
}
