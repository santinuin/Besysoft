package com.besysoft.ejerciciounidad6.services.implementations;

import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad6.domain.entity.Personaje;
import com.besysoft.ejerciciounidad6.dto.PersonajeDTO;
import com.besysoft.ejerciciounidad6.dto.mapper.PersonajeMapper;
import com.besysoft.ejerciciounidad6.excepciones.IdNotFoundException;
import com.besysoft.ejerciciounidad6.excepciones.ObjectAlreadyExistException;
import com.besysoft.ejerciciounidad6.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad6.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad6.services.interfaces.PersonajeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonajeServiceImpl extends GenericService<Personaje, PersonajeDTO> implements PersonajeService {

    private final PersonajeRepository repository;

    private final PeliculaRepository peliculaRepository;

    private final PersonajeMapper mapper;

    public PersonajeServiceImpl(PersonajeRepository repository, PeliculaRepository peliculaRepository, PersonajeMapper mapper) {
        this.repository = repository;
        this.peliculaRepository = peliculaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonajeDTO> findAll() {
        return this.mapper.toDTOList(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonajeDTO> findByNombre(String nombre) {
        return this.mapper.toDTOList(this.repository.findByNombreIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonajeDTO> findByEdad(Integer edad) {
        return this.mapper.toDTOList(this.repository.findByEdad(edad));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonajeDTO> findByEdadBetween(Integer desde, Integer hasta) {
        return this.mapper.toDTOList(this.repository.findByEdadBetween(desde, hasta));
    }

    @Override
    @Transactional(readOnly = true)
    public PersonajeDTO findById(Long id) throws IdNotFoundException {

        if(this.repository.findById(id).isEmpty()){
            throw new IdNotFoundException("Error: no se encontró, el personaje ID: "
                    .concat(id.toString().concat(" no existe.")));
        }

        return this.mapper.toDTO(this.repository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Personaje save(PersonajeDTO personaje) {

        List<Personaje> personajesList = this.repository.findByNombreIgnoreCase(personaje.getNombre());

        if (!personajesList.isEmpty()) {
            throw new ObjectAlreadyExistException(
                    String.format("El personaje %s ya existe", personaje.getNombre()));
        }

        return this.repository.save(this.mapper.toEntity(personaje));
    }

    @Override
    @Transactional
    public Personaje update(Long id, PersonajeDTO personaje) throws IdNotFoundException {

        Personaje personajeUpdate = this.repository.findById(id).orElse(null);
        if(this.repository.findById(id).isEmpty()){
            throw new IdNotFoundException("Error: no se pudo editar, el personaje ID: "
                    .concat(id.toString().concat(" no existe.")));
        }

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
