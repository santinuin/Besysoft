package com.besysoft.ejerciciounidad5.services.implementations;

import com.besysoft.ejerciciounidad5.dto.GeneroDTO;
import com.besysoft.ejerciciounidad5.dto.mapper.GeneroMapper;
import com.besysoft.ejerciciounidad5.dto.mapper.PeliculaMapper;
import com.besysoft.ejerciciounidad5.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad5.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad5.services.interfaces.GeneroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.besysoft.ejerciciounidad5.Data.LoadData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeneroServiceImplTest {

    GeneroRepository repository;
    PeliculaRepository peliculaRepository;

    GeneroMapper mapper;
    PeliculaMapper peliculaMapper;

    GeneroService service;

    @BeforeEach
    void setUp() {
    repository = mock(GeneroRepository.class);
    peliculaRepository = mock(PeliculaRepository.class);

    mapper = mock(GeneroMapper.class);
    peliculaMapper = mock(PeliculaMapper.class);

    service = new GeneroServiceImpl(repository, peliculaRepository, mapper, peliculaMapper);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(GENEROS);

        List<GeneroDTO> generosDTO = service.findAll();

        //Verificar que sea un DTO
    }

    @Test
    void findPeliculasByGeneroNombre() {
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(GENERO_1));
        when(mapper.toDTO(GENERO_1)).thenReturn(GENERO_DTO_1);

        GeneroDTO byId = service.findById(1L);

        assertThat(byId.equals(GENERO_DTO_1));
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }
}