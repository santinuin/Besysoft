package com.besysoft.ejerciciounidad5.services.implementations;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.domain.entity.Personaje;
import com.besysoft.ejerciciounidad5.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad5.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad5.services.interfaces.PeliculaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Pelicula> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByTitulo(String titulo) {
        return this.repository.findByTituloIgnoreCase(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByDateBetween(Date desde, Date hasta) {
        return this.repository.findByFechaDeCreacionBetween(desde, hasta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta) {
        return this.repository.findByCalificacionBetween(desde, hasta);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Pelicula save(Pelicula pelicula) {

        if (!this.repository.findByTituloIgnoreCase(pelicula.getTitulo()).isEmpty()) {
            return null;
        }

        return this.repository.save(pelicula);
    }

    @Override
    @Transactional
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
