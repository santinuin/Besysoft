package com.besysoft.ejerciciounidad4.services.interfaces;

import com.besysoft.ejerciciounidad4.domain.entity.Personaje;

import java.util.List;

public interface PersonajeService {

    List<Personaje> findAll();

    Personaje save(Personaje personaje);

    Personaje update(Personaje personaje);

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(Integer edad);

    List<Personaje> findByEdadBetween(Integer desde, Integer hasta);

}

