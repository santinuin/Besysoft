package com.besysoft.ejerciciounidad4.repositories.database;

import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByFechaDeCreacionBetween(Date desde, Date hasta);

    List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta);

    List<Pelicula> findByTituloIgnoreCase(String nombre);

    @Query("select p from peliculas p where p.genero_id = 1")
    List<Pelicula> findPeliculasByGenero(Long id);

}
