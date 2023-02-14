package com.besysoft.ejerciciounidad4.repositories.database;

import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import org.springframework.data.repository.CrudRepository;

public interface PeliculaRepository extends CrudRepository<Pelicula, Long> {
}
