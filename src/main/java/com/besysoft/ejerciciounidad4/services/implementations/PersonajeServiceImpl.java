package com.besysoft.ejerciciounidad4.services.implementations;

import com.besysoft.ejerciciounidad4.domain.entity.Personaje;
import com.besysoft.ejerciciounidad4.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad4.services.interfaces.PersonajeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl extends GenericService<Personaje> implements PersonajeService {

    private final PersonajeRepository repository;

    public PersonajeServiceImpl(PersonajeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Personaje update(Personaje personaje) {

        if (personaje.getId() == null) {
            return null;
        }

        Optional<Personaje> oPersonaje = this.repository.findById(personaje.getId());

        /*personaje.setId(this.repository.findAll().size() + 1);*/
        return this.repository.save(oPersonaje.get());
    }

    @Override
    public Personaje save(Personaje personaje) {

        if (personaje.getId() != null) return null;

        return this.repository.save(personaje);
    }

    @Override
    public List<Personaje> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Personaje> findByNombre(String nombre) {
        return this.repository.findByNombre(nombre);
    }

    @Override
    public List<Personaje> findByEdad(Integer edad) {
        return this.repository.findByEdad(edad);
    }

    @Override
    public List<Personaje> findByEdadBetween(Integer desde, Integer hasta) {
        return this.repository.findByEdadBetween(desde, hasta);
    }

}
