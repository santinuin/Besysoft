package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad4.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.GeneroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroServiceImpl extends GenericService<Genero> implements GeneroService {

    private final GeneroRepository repository;

    private final PeliculaRepository peliculaRepository;

    public GeneroServiceImpl(GeneroRepository repository, PeliculaRepository peliculaRepository) {
        this.repository = repository;
        this.peliculaRepository = peliculaRepository;
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
    public Genero findById(Long id) {
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
    public Genero update(Long id, Genero genero) {

        Genero generoUpdate = this.repository.findById(id).orElse(null);

        if (!genero.getNombre().equals(generoUpdate.getNombre())) {
            generoUpdate.setNombre(genero.getNombre());
        }

        if (genero.getPeliculas() != null) {

            List<String> peliculaNombres = genero.getPeliculas().stream()
                    .map(Pelicula::getTitulo)
                    .collect(Collectors.toList());

            List<Pelicula> peliculas = this.peliculaRepository.findByTituloInIgnoreCase(peliculaNombres);

            generoUpdate.setPeliculas(peliculas);
        }

        return this.repository.save(generoUpdate);

    }

}
