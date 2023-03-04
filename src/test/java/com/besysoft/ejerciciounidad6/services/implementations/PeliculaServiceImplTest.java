package com.besysoft.ejerciciounidad6.services.implementations;

import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad6.domain.entity.Personaje;
import com.besysoft.ejerciciounidad6.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad6.dto.mapper.PeliculaMapper;
import com.besysoft.ejerciciounidad6.repositories.database.PeliculaRepository;
import com.besysoft.ejerciciounidad6.repositories.database.PersonajeRepository;
import com.besysoft.ejerciciounidad6.services.interfaces.PeliculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.besysoft.ejerciciounidad6.Data.LoadData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PeliculaServiceImplTest {

    @MockBean
    PeliculaRepository repository;

    @MockBean
    PersonajeRepository personajeRepository;

    @Autowired
    PeliculaMapper mapper;

    @Autowired
    PeliculaService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Buscar peliculas")
    void findAll() {

        when(repository.findAll()).thenReturn(PELICULAS);

        List<PeliculaDTO> result = service.findAll();

        assertEquals(11, result.size());
        assertEquals("Voley", result.get(0).getTitulo());
        assertEquals("Un novio para mi mujer", result.get(1).getTitulo());
    }

    @Test
    @DisplayName("Buscar peliculas por titulo")
    void findByTitulo() {

        String titulo = "Django";
        when(repository.findByTituloIgnoreCase(titulo)).thenReturn(List.of(PELICULA_11));

        List<PeliculaDTO> result = service.findByTitulo(titulo);

        verify(repository).findByTituloIgnoreCase(titulo);
        assertEquals(PELICULA_DTO_11.getTitulo(), result.get(0).getTitulo());
    }

    @Test
    @DisplayName("Buscar peliculas entre fechas")
    void findByDateBetween() {

        Date desde = fechaFormatter(12, 3, 2014);
        Date hasta = fechaFormatter(12, 3, 2016);
        List<Pelicula> peliculas = List.of(PELICULA_1);
        when(repository.findByFechaDeCreacionBetween(desde, hasta)).thenReturn(peliculas);

        List<PeliculaDTO> result = service.findByDateBetween(desde, hasta);

        assertEquals(1, result.size());
        assertThat(List.of(PELICULA_DTO_1).equals(result));
    }

    @Test
    @DisplayName("Buscar peliculas entre calificaciones")
    void findByCalificacionBetween() {

        List<Pelicula> peliculas = PELICULAS.stream().filter(x -> x.getCalificacion() >= 4 && x.getCalificacion() <= 5).collect(Collectors.toList());
        List<PeliculaDTO> peliculasDTO = PELICULAS_DTO.stream().filter(x -> x.getCalificacion() >= 4 && x.getCalificacion() <= 5).collect(Collectors.toList());

        when(repository.findByCalificacionBetween(4, 5)).thenReturn(peliculas);

        List<PeliculaDTO> result = service.findByCalificacionBetween(4, 5);

        assertEquals(8, result.size());
        assertEquals(peliculasDTO.get(0).getTitulo(), result.get(0).getTitulo());
    }

    @Test
    @DisplayName("Buscar pelicula por ID")
    void findById() {
        Long id = 10L;
        when(repository.findById(id)).thenReturn(Optional.of(PELICULA_10));

        PeliculaDTO result = service.findById(id);

        assertNotNull(result);
        assertEquals(PELICULA_DTO_10.getId(), result.getId());
        assertEquals(PELICULA_DTO_10.getTitulo(), result.getTitulo());
        assertEquals(PELICULA_DTO_10.getCalificacion(), result.getCalificacion());
    }

    @Test
    @DisplayName("Guardar pelicula")
    void save() {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setTitulo("El Padrino");
        when(repository.findByTituloIgnoreCase(peliculaDTO.getTitulo())).thenReturn(Collections.singletonList(new Pelicula()));

        Pelicula result = service.save(peliculaDTO);

        verify(repository).findByTituloIgnoreCase(peliculaDTO.getTitulo());
        verify(repository, never()).save(any(Pelicula.class));
        assertNull(result);

    }

    @Test
    @DisplayName("Modificar pelicula")
    void update() {

            Long id = 1L;
            PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTO.setTitulo("Voley 2");
            peliculaDTO.setCalificacion(2);
            List<Personaje> personajes = List.of(PERSONAJE_1, PERSONAJE_2, PERSONAJE_3);
            when(repository.findById(id)).thenReturn(Optional.of(PELICULA_1));
            when(repository.save(any(Pelicula.class))).thenReturn(PELICULA_1);
            when(personajeRepository.findByNombreInIgnoreCase(anyList())).thenReturn(personajes);

            Pelicula result = service.update(id, peliculaDTO);

            verify(repository).findById(id);
            verify(repository).save(PELICULA_1);
            assertEquals(PELICULA_1, result);
            assertEquals(peliculaDTO.getTitulo(), result.getTitulo());
            assertEquals(peliculaDTO.getFechaDeCreacion(), result.getFechaDeCreacion());
            assertEquals(peliculaDTO.getCalificacion(), result.getCalificacion());
        }

    }