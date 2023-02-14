package com.besysoft.ejerciciounidad4.repositories.memory;

import com.besysoft.ejerciciounidad4.domain.entity.Personaje;

import java.util.List;

public interface PersonajeRepository {

    List<Personaje> findAll();

    Personaje save(Personaje personaje);

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(Integer edad);

    List<Personaje> findByEdadBetween(Integer desde, Integer hasta);

    Personaje updatePersonaje(Long id, Personaje personaje);
}
