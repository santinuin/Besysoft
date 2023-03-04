package com.besysoft.ejerciciounidad5.controllers;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad5.services.interfaces.GeneroService;
import com.besysoft.ejerciciounidad5.services.interfaces.PeliculaService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PeliculaController.class)
class PeliculaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PeliculaService service;

    @MockBean
    private GeneroService generoService;

    private ObjectMapper objectMapper;

    private String url;


    @BeforeEach
    void setUp() {
        this.url = "/peliculas";
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void findAll() throws Exception {
        when(service.findAll()).thenReturn(PELICULAS_DTO);

        mvc.perform(get(this.url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findByDateBetween() throws Exception {

        when(service.findByDateBetween(any(), any())).thenReturn(List.of(PELICULA_DTO_7));

        MvcResult result = mvc.perform(get(this.url + "/fechas")
                        .param("desde", "12042019")
                        .param("hasta", "12042020")
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        List<PeliculaDTO> response = objectMapper.readValue(responseString, new TypeReference<>() {
        });
        assertEquals(List.of(PELICULA_DTO_7).get(0).getTitulo(), response.get(0).getTitulo());
    }

    @Test
    void findByCalificacionBetween() throws Exception {

        when(service.findByCalificacionBetween(1, 2)).thenReturn(List.of(PELICULA_DTO_6));

        MvcResult result = mvc.perform(get(this.url + "/calificacion")
                        .param("desde", "1")
                        .param("hasta", "2")
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        List<PeliculaDTO> response = objectMapper.readValue(responseString, new TypeReference<List<PeliculaDTO>>() {
        });
        assertEquals(List.of(PELICULA_DTO_6).get(0).getTitulo(), response.get(0).getTitulo());
    }

    @Test
    void savePelicula() throws Exception {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setTitulo("NuevaPelicula");
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("NuevaPelicula");

        when(service.save(peliculaDTO))
                .thenReturn(pelicula);

        System.out.println(this.objectMapper.writeValueAsString(peliculaDTO));

        mvc.perform(
                post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(peliculaDTO))

        ).andExpect(status().isCreated());
    }

    @Test
    void updatePelicula() throws Exception {

        Long id = 1L;
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setTitulo("Pelicula Actualizar");
        Pelicula peliculaActualizada = PELICULA_1;

        when(service.findById(id)).thenReturn(peliculaDTO);
        when(service.update(id, peliculaDTO)).thenReturn(PELICULA_1);

        mvc.perform(
                        put("/peliculas/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(peliculaDTO))
                )
                .andExpect(status().isOk());
    }
}