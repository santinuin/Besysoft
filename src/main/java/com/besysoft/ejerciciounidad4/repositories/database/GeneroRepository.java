package com.besysoft.ejerciciounidad4.repositories.database;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Genero findByNombreIgnoreCase(String nombre);

    @Query("select p from Pelicula p where p.genero_id = ?1")
    List<Pelicula> findPeliculasByGenero(Long id);
}
