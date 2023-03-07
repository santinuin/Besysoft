package com.besysoft.peliculasapp.services.implementations;

import com.besysoft.peliculasapp.domain.entity.Personaje;
import com.besysoft.peliculasapp.dto.PersonajeDTO;
import com.besysoft.peliculasapp.dto.mapper.PersonajeMapper;
import com.besysoft.peliculasapp.exceptions.IdNotFoundException;
import com.besysoft.peliculasapp.repositories.database.PeliculaRepository;
import com.besysoft.peliculasapp.repositories.database.PersonajeRepository;
import com.besysoft.peliculasapp.services.interfaces.PersonajeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.besysoft.peliculasapp.Data.LoadData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonajeServiceImplTest {

    @MockBean
    PersonajeRepository repository;

    @MockBean
    PeliculaRepository peliculaRepository;

    @Autowired
    PersonajeMapper mapper;

    @Autowired
    PersonajeService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Buscar personajes")
    void findAll() {
        when(repository.findAll()).thenReturn(PERSONAJES);

        List<PersonajeDTO> result = service.findAll();

        assertEquals(11, result.size());
        assertEquals(PERSONAJE_1.getId(), result.get(0).getId());
        assertEquals(PERSONAJE_1.getNombre(), result.get(0).getNombre());
        assertEquals(PERSONAJE_2.getId(), result.get(1).getId());
        assertEquals(PERSONAJE_2.getNombre(), result.get(1).getNombre());
    }

    @Test
    @DisplayName("Buscar peliculas por nombre")
    void findByNombre() {

        String nombre = "pilar";
        when(repository.findByNombreIgnoreCase(nombre)).thenReturn(List.of(PERSONAJE_1));

        List<PersonajeDTO> result = service.findByNombre(nombre);

        assertEquals(1, result.size());
        assertEquals(PERSONAJE_1.getId(), result.get(0).getId());
        assertEquals(PERSONAJE_1.getNombre(), result.get(0).getNombre());
    }

    @Test
    @DisplayName("Buscar personajes por edad")
    void findByEdad() {

        Integer edad = 30;
        when(repository.findByEdad(edad)).thenReturn(PERSONAJES.stream()
                .filter(x -> x.getEdad() == 30)
                .collect(Collectors.toList()));

        List<PersonajeDTO> result = service.findByEdad(edad);

        assertEquals(List.of(PERSONAJE_DTO_6), result);
    }

    @Test
    @DisplayName("Buscar personajes entre edades")
    void findByEdadBetween() {
        Integer desde = 29;
        Integer hasta = 30;
        when(repository.findByEdadBetween(desde, hasta)).thenReturn(PERSONAJES.stream()
                .filter(x -> x.getEdad() >= 29 && x.getEdad() <= 30)
                .collect(Collectors.toList()));

        List<PersonajeDTO> result = service.findByEdadBetween(desde, hasta);

        assertEquals(1, result.size());
        assertEquals(PERSONAJE_DTO_6.getNombre(), result.get(0).getNombre());
    }

    @Test
    @DisplayName("Buscar personaje por id")
    void findById() throws IdNotFoundException {

        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(PERSONAJE_1));

        PersonajeDTO result = service.findById(id);

        assertEquals(PERSONAJE_DTO_1.getId(), result.getId());
    }

    @Test
    @DisplayName("Guardar personaje")
    void save() {
        PersonajeDTO personajeDTO = new PersonajeDTO(12L, "Luke", 35, 72, "historia", null);

        when(repository.findByNombreIgnoreCase(personajeDTO.getNombre())).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(new Personaje(12L, "Luke", 35, 72, "historia", null));

        Personaje result = service.save(personajeDTO);

        assertNotNull(result);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Guardar personaje existente")
    public void testSaveConPersonajeExistente() {
        PersonajeDTO personajeDTO = new PersonajeDTO(12L, "Luke", 35, 72, "historia", null);
        when(repository.findByNombreIgnoreCase(personajeDTO.getNombre())).thenReturn(PERSONAJES);

        Personaje result = service.save(personajeDTO);

        assertNull(result);
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Modificar personaje")
    void update() throws IdNotFoundException {
        Long id = 1L;
        PersonajeDTO personajeDTO = new PersonajeDTO(1l, "Luke", 20, 72, "historia", null);
        PERSONAJE_1.setNombre(personajeDTO.getNombre());
        PERSONAJE_1.setEdad(personajeDTO.getEdad());
        PERSONAJE_1.setPeso(personajeDTO.getPeso());
        PERSONAJE_1.setHistoria(personajeDTO.getHistoria());
        PERSONAJE_1.setPelicula(personajeDTO.getPelicula());
        when(repository.findById(id)).thenReturn(Optional.of(PERSONAJE_1));
        when(peliculaRepository.findByTituloIgnoreCase(PELICULA_1.getTitulo())).thenReturn(List.of(PELICULA_1));
        when(repository.save(any())).thenReturn(PERSONAJE_1);

        Personaje result = service.update(id, personajeDTO);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(personajeDTO.getNombre(), result.getNombre());
        assertEquals(personajeDTO.getEdad(), result.getEdad());
        assertEquals(personajeDTO.getPeso(), result.getPeso());
        assertEquals(personajeDTO.getHistoria(), result.getHistoria());
    }

}