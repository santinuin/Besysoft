package com.besysoft.ejerciciounidad6.controllers;

import com.besysoft.ejerciciounidad6.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad6.excepciones.IdNotFoundException;
import com.besysoft.ejerciciounidad6.excepciones.ObjectAlreadyExistException;
import com.besysoft.ejerciciounidad6.services.interfaces.GeneroService;
import com.besysoft.ejerciciounidad6.services.interfaces.PeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private static final Logger logger = LoggerFactory.getLogger(PeliculaController.class);

    private final PeliculaService peliculaService;

    private final GeneroService generoService;

    public PeliculaController(PeliculaService peliculaService, GeneroService generoService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
    }

    @GetMapping()
    public ResponseEntity<List<PeliculaDTO>> findAll(@RequestParam(required = false) String titulo,
                                                     @RequestParam(required = false) String genero) {

        if (titulo != null && !titulo.isBlank()) return ResponseEntity.ok(this.peliculaService.findByTitulo(titulo));
        if (genero != null && !genero.isBlank())
            return ResponseEntity.ok(this.generoService.findPeliculasByGeneroNombre(genero));

        return ResponseEntity.ok(this.peliculaService.findAll());
    }

    @GetMapping("/fechas")
    public ResponseEntity<?> findByDateBetween(@DateTimeFormat(pattern = "ddMMyyyy") @RequestParam Date desde,
                                               @DateTimeFormat(pattern = "ddMMyyyy") @RequestParam Date hasta) {

        Map<String, Object> response = new HashMap<>();

        if (this.peliculaService.findByDateBetween(desde, hasta).isEmpty()) {

            response.put("succes", Boolean.TRUE);
            response.put("mensaje", "No existen peliculas entre esas fechas");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(this.peliculaService.findByDateBetween(desde, hasta), HttpStatus.OK);
    }

    @GetMapping("/calificacion")
    public ResponseEntity<?> findByCalificacionBetween(@RequestParam Integer desde,
                                                       @RequestParam Integer hasta) {

        Map<String, Object> response = new HashMap<>();

        if (desde < 1 || hasta > 5) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "Ingresar calificacion del 1 al 5");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (this.peliculaService.findByCalificacionBetween(desde, hasta).isEmpty()) {

            response.put("succes", Boolean.TRUE);
            response.put("mensaje", "No existen peliculas con esa calificacion");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return ResponseEntity.ok(this.peliculaService.findByCalificacionBetween(desde, hasta));
    }

    @PostMapping
    public ResponseEntity<?> savePelicula(@Valid @RequestBody PeliculaDTO pelicula, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }
       /* if (pelicula.getTitulo() == null || pelicula.getTitulo().isBlank()) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "No es posible crear una pelicula sin titulo");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }*/

        /*if (this.peliculaService.save(pelicula) == null) {
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "La pelicula " + pelicula.getTitulo() + " ya existe");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }*/
        try {
            this.peliculaService.save(pelicula);
        } catch (ObjectAlreadyExistException e) {
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡La pelicula " + pelicula.getTitulo() + " ha sido creada con éxito!");


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePelicula(@PathVariable Long id,
                                            @Valid @RequestBody PeliculaDTO pelicula, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }

        /*if (this.peliculaService.findById(id) == null) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "Error: no se pudo editar, la película ID: "
                    .concat(id.toString().concat(" no existe.")));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }*/

        /*if (pelicula.getTitulo() == null || pelicula.getTitulo().isBlank()) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "El campo titulo no puede estar vacío");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }*/
        try {
            this.peliculaService.update(id, pelicula);
        } catch (IdNotFoundException e) {
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡La pelicula " + pelicula.getTitulo() + " ha sido modificada con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
