package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.repositories.memory.PeliculaRepository;
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

        Optional<Pelicula> oPelicula = this.repository.findAll().stream()
                .filter(x -> x.getTitulo().equals(pelicula.getTitulo()))
                .findFirst();

        if (oPelicula.isPresent()) {
            return null;
        }
        pelicula.setId(this.repository.findAll().size() + 1);
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
        return this.repository.findByDateBetween(desde, hasta);
    }

    @Override
    public List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta) {
        return this.repository.findByCalificacionBetween(desde, hasta);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {

        if (this.repository.findAll().stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .isEmpty()) {
            return null;
        }

        return this.repository.updatePelicula(id, pelicula);
    }
}
