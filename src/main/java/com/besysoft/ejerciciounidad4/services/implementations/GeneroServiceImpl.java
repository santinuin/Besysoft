package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.GeneroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl extends GenericService<Genero> implements GeneroService {

    private final GeneroRepository repository;

    public GeneroServiceImpl(GeneroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genero save(Genero genero) {
        if (this.repository.findByNombreIgnoreCase(genero.getNombre()) != null) {
            return null;
        }

        return this.repository.save(genero);
    }

    @Override
    public List<Genero> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Pelicula> findByGenero(String nombre) {
        return this.repository.findPeliculasByGenero(this.repository.findByNombreIgnoreCase(nombre).getId());
    }

    @Override
    public Genero findById(Long id){
        return this.repository.findById(id).orElseThrow();
    }

}
