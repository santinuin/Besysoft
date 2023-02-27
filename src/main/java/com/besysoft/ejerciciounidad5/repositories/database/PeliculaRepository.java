package com.besysoft.ejerciciounidad5.repositories.database;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {


    List<Pelicula> findByFechaDeCreacionBetween(Date desde, Date hasta);

    List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta);

    List<Pelicula> findByTituloIgnoreCase(String nombre);

    List<Pelicula> findByTituloInIgnoreCase(List<String> peliculaTitulos);
}
