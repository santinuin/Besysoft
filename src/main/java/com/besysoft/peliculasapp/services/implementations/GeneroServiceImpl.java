package com.besysoft.peliculasapp.services.implementations;

import com.besysoft.peliculasapp.domain.entity.Genero;
import com.besysoft.peliculasapp.domain.entity.Pelicula;
import com.besysoft.peliculasapp.dto.GeneroDTO;
import com.besysoft.peliculasapp.dto.PeliculaDTO;
import com.besysoft.peliculasapp.dto.mapper.GeneroMapper;
import com.besysoft.peliculasapp.dto.mapper.PeliculaMapper;
import com.besysoft.peliculasapp.exceptions.IdNotFoundException;
import com.besysoft.peliculasapp.exceptions.ObjectAlreadyExistException;
import com.besysoft.peliculasapp.repositories.database.GeneroRepository;
import com.besysoft.peliculasapp.repositories.database.PeliculaRepository;
import com.besysoft.peliculasapp.services.interfaces.GeneroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroServiceImpl extends GenericService<Genero, GeneroDTO> implements GeneroService {

    private final GeneroRepository repository;

    private final PeliculaRepository peliculaRepository;

    private final GeneroMapper mapper;

    private final PeliculaMapper peliculaMapper;
    
    public GeneroServiceImpl(GeneroRepository repository, PeliculaRepository peliculaRepository, GeneroMapper mapper, PeliculaMapper peliculaMapper) {
        this.repository = repository;
        this.peliculaRepository = peliculaRepository;
        this.mapper = mapper;
        this.peliculaMapper = peliculaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneroDTO> findAll() {
        return this.mapper.toDTOList(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaDTO> findPeliculasByGeneroNombre(String nombre) {
        return this.peliculaMapper.toDTOList(this.repository.findByNombreIgnoreCase(nombre).getPeliculas());
    }

    @Override
    @Transactional(readOnly = true)
    public GeneroDTO findById(Long id) throws IdNotFoundException {

        if(this.repository.findById(id).isEmpty()){
            throw new IdNotFoundException("Error: no se encontró, el genero ID: "
                    .concat(id.toString().concat(" no existe.")));
        }

        return this.mapper.toDTO(this.repository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Genero save(GeneroDTO genero) {

        Optional<Genero> oGenero = Optional.ofNullable(this.repository.findByNombreIgnoreCase(genero.getNombre()));

        if (oGenero.isPresent()) {
            throw new ObjectAlreadyExistException(
                    String.format("El genero %s ya existe", genero.getNombre()));
        }

        return this.repository.save(this.mapper.toEntity(genero));
    }

    @Override
    @Transactional
    public Genero update(Long id, GeneroDTO genero) throws IdNotFoundException {

        Genero generoUpdate = this.repository.findById(id).orElse(null);

        if(this.repository.findById(id).isEmpty()){
            throw new IdNotFoundException("Error: no se pudo editar, el genero ID: "
                            .concat(id.toString().concat(" no existe.")));
        }

        if (!genero.getNombre().equals(generoUpdate.getNombre())) {
            generoUpdate.setNombre(genero.getNombre());
        }

        if (genero.getPeliculas() != null) {

            List<String> peliculaNombres = genero.getPeliculas().stream()
                    .map(Pelicula::getTitulo)
                    .collect(Collectors.toList());

            List<Pelicula> peliculas = this.peliculaRepository.findByTituloInIgnoreCase(peliculaNombres);

            generoUpdate.setPeliculas(peliculas);
        }

        return this.repository.save(generoUpdate);

    }

}
