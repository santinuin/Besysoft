package com.besysoft.ejerciciounidad5.services.interfaces;

import com.besysoft.ejerciciounidad5.domain.entity.Personaje;

import java.util.List;

public interface PersonajeService {

    List<Personaje> findAll();

    Personaje save(Personaje personaje);

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(Integer edad);

    List<Personaje> findByEdadBetween(Integer desde, Integer hasta);

    Personaje findById(Long id);

    Personaje update(Long id, Personaje personaje);
}

