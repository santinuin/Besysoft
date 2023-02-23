package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.GeneroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl extends GenericService<Genero> implements GeneroService {

    private final GeneroRepository repository;

    public GeneroServiceImpl(GeneroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genero> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Pelicula> findPeliculasByGeneroNombre(String nombre) {
        return this.repository.findByNombreIgnoreCase(nombre).getPeliculas();
    }

    @Override
    public Genero findById(Long id){
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Genero save(Genero genero) {

        Optional<Genero> oGenero = Optional.ofNullable(this.repository.findByNombreIgnoreCase(genero.getNombre()));

        if (oGenero.isPresent()) {
            return null;
        }

        return this.repository.save(genero);
    }

    @Override
    public Genero update(Genero genero) {

        return this.repository.save(genero);

    }

}
