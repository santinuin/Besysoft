package com.besysoft.peliculasapp.repositories.database;

import com.besysoft.peliculasapp.domain.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findByEdad(Integer edad);

    List<Personaje> findByEdadBetween(Integer desde, Integer hasta);

    List<Personaje> findByNombreIgnoreCase(String nombre);

    List<Personaje> findByNombreInIgnoreCase(List<String> personajeNombres);
}
