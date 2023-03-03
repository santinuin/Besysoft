package com.besysoft.ejerciciounidad5.services.implementations;

import com.besysoft.ejerciciounidad5.domain.entity.Genero;
import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.dto.GeneroDTO;
import com.besysoft.ejerciciounidad5.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad5.dto.mapper.GeneroMapper;
import com.besysoft.ejerciciounidad5.dto.mapper.PeliculaMapper;
import com.besysoft.ejerciciounidad5.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad5.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad5.services.interfaces.GeneroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.besysoft.ejerciciounidad5.Data.LoadData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @DisplayName("Buscar generos y devolver como DTOs")
    void findAll() {
        when(repository.findAll()).thenReturn(GENEROS);
        when(mapper.toDTOList(GENEROS)).thenReturn(GENEROS_DTO);

        List<GeneroDTO> result = service.findAll();

        assertEquals(GENEROS_DTO, result);
    }

    @Test
    @DisplayName("Buscar peliculas por nombre de genero y devolver como DTOs")
    void findPeliculasByGeneroNombre() {

        String nombreGenero = "terror";
        List<PeliculaDTO> peliculasTerror = List.of(PELICULA_DTO_4, PELICULA_DTO_5, PELICULA_DTO_6);
        when(repository.findByNombreIgnoreCase(nombreGenero)).thenReturn(GENERO_2);
        when(peliculaMapper.toDTOList(GENERO_2.getPeliculas())).thenReturn(peliculasTerror);

        List<PeliculaDTO> result = service.findPeliculasByGeneroNombre("terror");

        assertEquals(peliculasTerror.size(), result.size());
        assertEquals(peliculasTerror.get(0).getId(), result.get(0).getId());
        assertEquals(peliculasTerror.get(0).getTitulo(), result.get(0).getTitulo());
        assertEquals(peliculasTerror.get(1).getId(), result.get(1).getId());
        assertEquals(peliculasTerror.get(1).getTitulo(), result.get(1).getTitulo());
    }

    @Test
    @DisplayName("Buscar Generos por ID ")
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(GENERO_1));
        when(mapper.toDTO(GENERO_1)).thenReturn(GENERO_DTO_1);

        GeneroDTO result = service.findById(1L);

        assertEquals(GENERO_DTO_1, result);
    }

    @Test
    @DisplayName("Guardar un genero")
    void save() {

        GeneroDTO nuevoGeneroDTO = new GeneroDTO(6L, "Cs. Ficcion", List.of(PELICULA_10));
        Genero nuevoGenero = new Genero(6L, "Cs. Ficcion", List.of(PELICULA_10));
        when(repository.findByNombreIgnoreCase("Cs. Ficcion")).thenReturn(null);
        when(mapper.toEntity(nuevoGeneroDTO)).thenReturn(nuevoGenero);
        when(repository.save(nuevoGenero)).thenReturn(nuevoGenero);

        Genero result = service.save(nuevoGeneroDTO);

        verify(repository, times(1)).findByNombreIgnoreCase("Cs. Ficcion");
        verify(mapper, times(1)).toEntity(nuevoGeneroDTO);
        verify(repository, times(1)).save(nuevoGenero);
        assertEquals(nuevoGeneroDTO.getId(), nuevoGenero.getId());
        assertEquals(nuevoGeneroDTO.getNombre(), nuevoGenero.getNombre());
        assertEquals(nuevoGeneroDTO.getPeliculas(), nuevoGenero.getPeliculas());
        assertEquals(nuevoGenero, result);

    }

    @Test
    @DisplayName("Guardar si el genero ya existe")
    public void saveAlreadyExists() {
        when(repository.findByNombreIgnoreCase("Drama")).thenReturn(GENERO_3);

        Genero result = service.save(GENERO_DTO_3);

        verify(repository, times(1)).findByNombreIgnoreCase("Drama");
        assertNull(result);
    }

    @Test
    @DisplayName("Modificar genero")
    void update() {

        Long id = 2L;
        GeneroDTO genero = new GeneroDTO(null, "Cs. Ficcion", null);
        GENERO_2.setNombre(genero.getNombre());
        List<String> peliculaNombres = GENERO_2.getPeliculas().stream()
                .map(Pelicula::getTitulo)
                .collect(Collectors.toList());
        List<Pelicula> peliculaList = List.of(PELICULA_4, PELICULA_5, PELICULA_6);

        when(repository.findById(id)).thenReturn(Optional.of(GENERO_2));
        when(peliculaRepository.findByTituloInIgnoreCase(peliculaNombres)).thenReturn(peliculaList);
        when(repository.save(GENERO_2)).thenReturn(GENERO_2);

        Genero result = service.update(id, genero);

        assertNotNull(result);
        assertEquals(GENERO_2.getNombre(), result.getNombre());
        assertEquals(peliculaList, result.getPeliculas());

    }
}