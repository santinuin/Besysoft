package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Personaje;
import com.besysoft.ejerciciounidad4.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.PersonajeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl extends GenericService<Personaje> implements PersonajeService {

    private final PersonajeRepository repository;

    public PersonajeServiceImpl(PersonajeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Personaje save(Personaje personaje) {

        List<Personaje> personajesList = this.repository.findByNombreIgnoreCase(personaje.getNombre());

        if (!personajesList.isEmpty()) {
            return null;
        }

        return this.repository.save(personaje);
    }

    @Override
    public List<Personaje> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Personaje> findByNombre(String nombre) {
        return this.repository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public List<Personaje> findByEdad(Integer edad) {
        return this.repository.findByEdad(edad);
    }

    @Override
    public List<Personaje> findByEdadBetween(Integer desde, Integer hasta) {
        return this.repository.findByEdadBetween(desde, hasta);
    }

    @Override
    public Personaje findById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }


}
