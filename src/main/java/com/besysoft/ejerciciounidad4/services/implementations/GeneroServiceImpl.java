package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.repositories.memory.GeneroRepository;
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
    public Genero save(Genero genero) {
        return null;
    }

    @Override
    public Genero update(Genero genero) {
        Optional<Genero> oGenero = this.repository.findAll().stream()
                .filter(x -> x.getNombre().equals(genero.getNombre()))
                .findFirst();

        if (oGenero.isPresent()) {
            return null;
        }
        genero.setId(this.repository.findAll().size() + 1);
        return this.repository.save(genero);
    }

    @Override
    public List<Genero> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Pelicula> findByGenero(String nombre) {
        return this.repository.findByGenero(nombre);
    }

    @Override
    public Genero updateGenero(Long id, Genero genero) {
        if (this.repository.findAll().stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .isEmpty()) {
            return null;
        }
        return this.repository.updateGenero(id, genero);
    }
}
