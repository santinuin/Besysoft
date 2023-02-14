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
    public Personaje save(Personaje personaje) {
        Optional<Personaje> oPersonaje = this.repository.findAll().stream()
                .filter(x -> x.getNombre().equals(personaje.getNombre()))
                .findFirst();

        if (oPersonaje.isPresent()) {
            return null;
        }
        personaje.setId(this.repository.findAll().size() + 1);
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

    @Override
    public Personaje updatePersonaje(Long id, Personaje personaje) {

        if (this.repository.findAll().stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .isEmpty()) {
            return null;
        }

        return this.repository.updatePersonaje(id, personaje);
    }

}
