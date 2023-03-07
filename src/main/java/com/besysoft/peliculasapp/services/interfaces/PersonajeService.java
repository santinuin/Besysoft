package com.besysoft.peliculasapp.services.interfaces;

import com.besysoft.peliculasapp.domain.entity.Personaje;
import com.besysoft.peliculasapp.dto.PersonajeDTO;
import com.besysoft.peliculasapp.exceptions.IdNotFoundException;

import java.util.List;

public interface PersonajeService {

    List<PersonajeDTO> findAll();

    List<PersonajeDTO> findByNombre(String nombre);

    List<PersonajeDTO> findByEdad(Integer edad);

    List<PersonajeDTO> findByEdadBetween(Integer desde, Integer hasta);

    PersonajeDTO findById(Long id) throws IdNotFoundException;

    Personaje save(PersonajeDTO personaje);

    Personaje update(Long id, PersonajeDTO personaje) throws IdNotFoundException;
}

