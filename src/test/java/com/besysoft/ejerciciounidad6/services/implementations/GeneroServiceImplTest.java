package com.besysoft.ejerciciounidad6.services.implementations;

import com.besysoft.ejerciciounidad6.domain.entity.Genero;
import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad6.dto.GeneroDTO;
import com.besysoft.ejerciciounidad6.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad6.dto.mapper.GeneroMapper;
import com.besysoft.ejerciciounidad6.dto.mapper.PeliculaMapper;
import com.besysoft.ejerciciounidad6.excepciones.IdNotFoundException;
import com.besysoft.ejerciciounidad6.repositories.database.GeneroRepository;
import com.besysoft.ejerciciounidad6.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad6.services.interfaces.GeneroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.besysoft.ejerciciounidad6.Data.LoadData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeneroServiceImplTest {

    @MockBean
    GeneroRepository repository;
    @MockBean
    PeliculaRepository peliculaRepository;

    @Autowired
    GeneroMapper mapper;
    @Autowired
    PeliculaMapper peliculaMapper;

    @Autowired
    GeneroService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Buscar generos")
    void findAll() {
        when(repository.findAll()).thenReturn(GENEROS);

        List<GeneroDTO> result = service.findAll();

        assertEquals(GENEROS_DTO, result);
    }

    @Test
    @DisplayName("Buscar peliculas por nombre de genero")
    void findPeliculasByGeneroNombre() {

        String nombreGenero = "terror";
        List<PeliculaDTO> peliculasTerror = List.of(PELICULA_DTO_4, PELICULA_DTO_5, PELICULA_DTO_6);
        when(repository.findByNombreIgnoreCase(nombreGenero)).thenReturn(GENERO_2);

        List<PeliculaDTO> result = service.findPeliculasByGeneroNombre("terror");

        assertEquals(peliculasTerror.size(), result.size());
        assertEquals(peliculasTerror.get(0).getId(), result.get(0).getId());
        assertEquals(peliculasTerror.get(0).getTitulo(), result.get(0).getTitulo());
        assertEquals(peliculasTerror.get(1).getId(), result.get(1).getId());
        assertEquals(peliculasTerror.get(1).getTitulo(), result.get(1).getTitulo());
    }

    @Test
    @DisplayName("Buscar genero por ID")
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(GENERO_1));

        GeneroDTO result = null;
        try {
            result = service.findById(1L);
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(GENERO_DTO_1, result);
    }

    @Test
    @DisplayName("Guardar genero")
    void save() {

        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setNombre("Drama");
        Genero generoGuardado = new Genero();
        generoGuardado.setId(1L);
        generoGuardado.setNombre("Drama");
        when(repository.findByNombreIgnoreCase(generoDTO.getNombre())).thenReturn(null);
        when(repository.save(any())).thenReturn(generoGuardado);

        Genero result = service.save(generoDTO);

        assertNotNull(result);
        assertEquals(generoGuardado.getId(), result.getId());
        assertEquals(generoGuardado.getNombre(), result.getNombre());

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

        Genero result = null;
        try {
            result = service.update(id, genero);
        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(result);
        assertEquals(GENERO_2.getNombre(), result.getNombre());
        assertEquals(peliculaList, result.getPeliculas());

    }
}