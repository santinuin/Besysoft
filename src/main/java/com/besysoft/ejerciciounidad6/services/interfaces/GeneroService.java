package com.besysoft.ejerciciounidad6.services.interfaces;

import com.besysoft.ejerciciounidad6.domain.entity.Genero;
import com.besysoft.ejerciciounidad6.dto.GeneroDTO;
import com.besysoft.ejerciciounidad6.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad6.exceptions.IdNotFoundException;

import java.util.List;

public interface GeneroService {

    Genero save(GeneroDTO genero);

    List<GeneroDTO> findAll();

    List<PeliculaDTO> findPeliculasByGeneroNombre(String nombre);

    GeneroDTO findById(Long id) throws IdNotFoundException;

    Genero update(Long id, GeneroDTO genero) throws IdNotFoundException;
}
