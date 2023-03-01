package com.besysoft.ejerciciounidad5.services.interfaces;

import com.besysoft.ejerciciounidad5.domain.entity.Genero;
import com.besysoft.ejerciciounidad5.dto.GeneroDTO;
import com.besysoft.ejerciciounidad5.dto.PeliculaDTO;

import java.util.List;

public interface GeneroService {

    Genero save(Genero genero);

    List<GeneroDTO> findAll();

    List<PeliculaDTO> findPeliculasByGeneroNombre(String nombre);

    Genero findById(Long id);

    Genero update(Long id, Genero genero);
}
