package com.besysoft.ejerciciounidad5.controllers;

import com.besysoft.ejerciciounidad5.domain.entity.Personaje;
import com.besysoft.ejerciciounidad5.dto.PersonajeDTO;
import com.besysoft.ejerciciounidad5.services.interfaces.PersonajeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.besysoft.ejerciciounidad5.Data.LoadData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonajeController.class)
class PersonajeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonajeService service;

    private ObjectMapper objectMapper;

    private String url;

    @BeforeEach
    void setUp() {
        this.url = "/personajes";
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void findAll() throws Exception {
        when(service.findAll()).thenReturn(PERSONAJES_DTO);

        mvc.perform(get(this.url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void findByEdadBetween() throws Exception {

        when(service.findByEdadBetween(30, 30)).thenReturn(List.of(PERSONAJE_DTO_1, PERSONAJE_DTO_4, PERSONAJE_DTO_6));

        MvcResult result = mvc.perform(get(this.url + "/edad")
                        .param("desde", "30")
                        .param("hasta", "30")
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        List<PersonajeDTO> response = objectMapper.readValue(responseString, new TypeReference<>() {
        });
        assertEquals(List.of(PERSONAJE_DTO_1, PERSONAJE_DTO_4, PERSONAJE_DTO_6), response);

    }

    @Test
    void savePersonaje() throws Exception {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setNombre("NuevoPersonaje");
        Personaje personaje = new Personaje();
        personaje.setNombre("NuevoPersonaje");

        when(service.save(personajeDTO))
                .thenReturn(personaje);

        System.out.println(this.objectMapper.writeValueAsString(personajeDTO));

        mvc.perform(
                post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(personajeDTO))

        ).andExpect(status().isCreated());
    }

    @Test
    void updatePersonaje() throws Exception {

        Long id = 1L;
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setNombre("Personaje Actualizar");
        PersonajeDTO personajeActualizad = PERSONAJE_DTO_1;

        when(service.findById(id)).thenReturn(personajeDTO);
        when(service.update(id, personajeDTO)).thenReturn(PERSONAJE_1);

        mvc.perform(
                        put("/personajes/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(personajeDTO))
                )
                .andExpect(status().isOk());
    }
}