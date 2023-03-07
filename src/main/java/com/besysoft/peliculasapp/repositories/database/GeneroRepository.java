package com.besysoft.peliculasapp.repositories.database;

import com.besysoft.peliculasapp.domain.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Genero findByNombreIgnoreCase(String nombre);

}
