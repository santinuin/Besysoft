package com.besysoft.ejerciciounidad5.services.implementations;

import com.besysoft.ejerciciounidad5.domain.entity.Genero;
import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad5.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad5.services.interfaces.GeneroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findPeliculasByGeneroNombre(String nombre) {
        return this.repository.findByNombreIgnoreCase(nombre).getPeliculas();
    }

    @Override
    @Transactional(readOnly = true)
    public Genero findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Genero save(Genero genero) {

        Optional<Genero> oGenero = Optional.ofNullable(this.repository.findByNombreIgnoreCase(genero.getNombre()));

        if (oGenero.isPresent()) {
            return null;
        }

        return this.repository.save(genero);
    }

    @Override
    @Transactional
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
