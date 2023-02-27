package com.besysoft.ejerciciounidad5.repositories.database;

import com.besysoft.ejerciciounidad5.domain.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Genero findByNombreIgnoreCase(String nombre);

}
