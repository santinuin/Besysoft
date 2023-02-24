package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad4.domain.entity.Personaje;
import com.besysoft.ejerciciounidad4.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad4.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.PeliculaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl extends GenericService<Pelicula> implements PeliculaService {

    private final PeliculaRepository repository;

    private final PersonajeRepository personajeRepository;

    public PeliculaServiceImpl(PeliculaRepository repository, PersonajeRepository personajeRepository) {
        this.repository = repository;
        this.personajeRepository = personajeRepository;
    }

    @Override
    public List<Pelicula> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Pelicula> findByTitulo(String titulo) {
        return this.repository.findByTituloIgnoreCase(titulo);
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
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Pelicula save(Pelicula pelicula) {

        if (!this.repository.findByTituloIgnoreCase(pelicula.getTitulo()).isEmpty()) {
            return null;
        }

        return this.repository.save(pelicula);
    }

    @Override
    public Pelicula update(Long id, Pelicula pelicula) {

        Pelicula peliculaUpdate = this.repository.findById(id).orElse(null);

        if (!pelicula.getTitulo().equals(peliculaUpdate.getTitulo())) {
            peliculaUpdate.setTitulo(pelicula.getTitulo());
        }

        peliculaUpdate.setFechaDeCreacion(pelicula.getFechaDeCreacion());
        peliculaUpdate.setCalificacion(pelicula.getCalificacion());

        if (pelicula.getPersonajes() != null) {

            List<String> personajeNombres = pelicula.getPersonajes().stream()
                    .map(Personaje::getNombre)
                    .collect(Collectors.toList());

            List<Personaje> personajes = this.personajeRepository.findByNombreInIgnoreCase(personajeNombres);

            peliculaUpdate.setPersonajes(personajes);
        }

        return this.repository.save(peliculaUpdate);

    }

}
