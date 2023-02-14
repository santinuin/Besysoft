package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.PeliculaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl extends GenericService<Pelicula> implements PeliculaService {

    private final PeliculaRepository repository;

    public PeliculaServiceImpl(PeliculaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pelicula save(Pelicula pelicula) {

        if (!this.repository.findByTituloIgnoreCase(pelicula.getTitulo()).isEmpty()) {
            return null;
        }

        return this.repository.save(pelicula);
    }

    @Override
    public List<Pelicula> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Pelicula> findByTitulo(String titulo) {
        return this.repository.findByTitulo(titulo);
    }

    @Override
    public List<Pelicula> findByDateBetween(Date desde, Date hasta) {
        return this.repository.findByFechaDeCreacionBetween(desde, hasta);
    }

    @Override
    public List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta) {
        return this.repository.findByCalificacionBetween(desde, hasta);
    }

    @Override
    public Pelicula findById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

}
