package com.besysoft.ejerciciounidad5.services.implementations;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.domain.entity.Personaje;
import com.besysoft.ejerciciounidad5.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad5.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad5.services.interfaces.PersonajeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonajeServiceImpl extends GenericService<Personaje> implements PersonajeService {

    private final PersonajeRepository repository;

    private final PeliculaRepository peliculaRepository;

    public PersonajeServiceImpl(PersonajeRepository repository, PeliculaRepository peliculaRepository) {
        this.repository = repository;
        this.peliculaRepository = peliculaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findByNombre(String nombre) {
        return this.repository.findByNombreIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findByEdad(Integer edad) {
        return this.repository.findByEdad(edad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findByEdadBetween(Integer desde, Integer hasta) {
        return this.repository.findByEdadBetween(desde, hasta);
    }

    @Override
    @Transactional(readOnly = true)
    public Personaje findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Personaje save(Personaje personaje) {

        List<Personaje> personajesList = this.repository.findByNombreIgnoreCase(personaje.getNombre());

        if (!personajesList.isEmpty()) {
            return null;
        }

        return this.repository.save(personaje);
    }

    @Override
    @Transactional
    public Personaje update(Long id, Personaje personaje) {

        Personaje personajeUpdate = this.repository.findById(id).orElse(null);

        if (!personaje.getNombre().equals(personajeUpdate.getNombre())) {
            personajeUpdate.setNombre(personaje.getNombre());
        }

        personajeUpdate.setEdad(personaje.getEdad());
        personajeUpdate.setPeso(personaje.getPeso());
        personajeUpdate.setHistoria(personaje.getHistoria());

        if (personaje.getPelicula() != null) {

            Pelicula pelicula = this.peliculaRepository.findByTituloIgnoreCase(personaje.getPelicula().getTitulo())
                    .stream()
                    .findFirst()
                    .orElse(null);

            personajeUpdate.setPelicula(pelicula);
        }

        return this.repository.save(personajeUpdate);
    }


}
