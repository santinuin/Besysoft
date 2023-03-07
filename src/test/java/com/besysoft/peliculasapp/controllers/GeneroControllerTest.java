package com.besysoft.peliculasapp.controllers;

import com.besysoft.peliculasapp.domain.entity.Genero;
import com.besysoft.peliculasapp.dto.GeneroDTO;
import com.besysoft.peliculasapp.services.interfaces.GeneroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.besysoft.peliculasapp.Data.LoadData.GENEROS_DTO;
import static com.besysoft.peliculasapp.Data.LoadData.GENERO_1;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GeneroController.class)
class GeneroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GeneroService service;

    private ObjectMapper objectMapper;

    private String url;

    @BeforeEach
    public void setUp() {
        this.url = "/genero";
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testFindAll() throws Exception {
        when(service.findAll()).thenReturn(GENEROS_DTO);

        mvc.perform(get(this.url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Comedia"));

        verify(service).findAll();
    }


    @Test
    void saveGenero() throws Exception {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setNombre("NuevoGenero");
        Genero genero = new Genero();
        genero.setNombre("NuevoGenero");

        when(service.save(generoDTO))
                .thenReturn(genero);

        System.out.println(this.objectMapper.writeValueAsString(generoDTO));

        mvc.perform(
                post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(generoDTO))

        ).andExpect(status().isCreated());
    }

    @Test
    void updateGenero() throws Exception {
        Long id = 1L;
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setNombre("Genero Actualizar");
        Genero generoActualizado = GENERO_1;

        when(service.findById(id)).thenReturn(generoDTO);
        when(service.update(id, generoDTO)).thenReturn(GENERO_1);

        mvc.perform(
                        put("/genero/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(generoDTO))
                )
                .andExpect(status().isOk());
    }
}