package com.besysoft.ejerciciounidad5.services.implementations;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.domain.entity.Personaje;
import com.besysoft.ejerciciounidad5.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad5.dto.mapper.PeliculaMapper;
import com.besysoft.ejerciciounidad5.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad5.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad5.services.interfaces.PeliculaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl extends GenericService<Pelicula, PeliculaDTO> implements PeliculaService {

    private final PeliculaRepository repository;

    private final PersonajeRepository personajeRepository;

    private final PeliculaMapper mapper;

    public PeliculaServiceImpl(PeliculaRepository repository, PersonajeRepository personajeRepository, PeliculaMapper mapper) {
        this.repository = repository;
        this.personajeRepository = personajeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaDTO> findAll() {
        return this.mapper.toDTOList(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaDTO> findByTitulo(String titulo) {
        return this.mapper.toDTOList(this.repository.findByTituloIgnoreCase(titulo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaDTO> findByDateBetween(Date desde, Date hasta) {
        return this.mapper.toDTOList(this.repository.findByFechaDeCreacionBetween(desde, hasta));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaDTO> findByCalificacionBetween(Integer desde, Integer hasta) {
        return this.mapper.toDTOList(this.repository.findByCalificacionBetween(desde, hasta));
    }

    @Override
    @Transactional(readOnly = true)
    public PeliculaDTO findById(Long id) {
        return this.mapper.toDTO(this.repository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Pelicula save(PeliculaDTO pelicula) {

        if (!this.repository.findByTituloIgnoreCase(pelicula.getTitulo()).isEmpty()) {
            return null;
        }

        return this.repository.save(this.mapper.toEntity(pelicula));
    }

    @Override
    @Transactional
    public Pelicula update(Long id, PeliculaDTO pelicula) {

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
