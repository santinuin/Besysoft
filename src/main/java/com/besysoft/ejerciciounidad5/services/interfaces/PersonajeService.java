package com.besysoft.ejerciciounidad5.services.interfaces;

import com.besysoft.ejerciciounidad5.domain.entity.Personaje;
import com.besysoft.ejerciciounidad5.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeService {

    List<PersonajeDTO> findAll();

    Personaje save(Personaje personaje);

    List<PersonajeDTO> findByNombre(String nombre);

    List<PersonajeDTO> findByEdad(Integer edad);

    List<PersonajeDTO> findByEdadBetween(Integer desde, Integer hasta);

    PersonajeDTO findById(Long id);

    Personaje update(Long id, Personaje personaje);
}

