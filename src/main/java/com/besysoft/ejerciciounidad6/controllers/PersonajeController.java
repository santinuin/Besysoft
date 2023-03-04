package com.besysoft.ejerciciounidad6.controllers;

import com.besysoft.ejerciciounidad6.dto.PersonajeDTO;
import com.besysoft.ejerciciounidad6.excepciones.IdNotFoundException;
import com.besysoft.ejerciciounidad6.excepciones.ObjectAlreadyExistException;
import com.besysoft.ejerciciounidad6.services.interfaces.PersonajeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private static final Logger logger = LoggerFactory.getLogger(PersonajeController.class);

    private final PersonajeService service;

    public PersonajeController(PersonajeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> findAll(@RequestParam(required = false) String nombre,
                                                      @RequestParam(required = false) Integer edad) {

        if (nombre != null && !nombre.isBlank()) return ResponseEntity.ok(this.service.findByNombre(nombre));
        if (edad != null) return ResponseEntity.ok(this.service.findByEdad(edad));

        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/edad")
    public ResponseEntity<?> findByEdadBetween(@RequestParam Integer desde,
                                               @RequestParam Integer hasta) {

        Map<String, Object> response = new HashMap<>();

        if (this.service.findByEdadBetween(desde, hasta).isEmpty()) {

            response.put("succes", Boolean.TRUE);
            response.put("mensaje", "No existen personajes de esa edad");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return ResponseEntity.ok(this.service.findByEdadBetween(desde, hasta));
    }

    @PostMapping
    public ResponseEntity<?> savePersonaje(@Valid @RequestBody PersonajeDTO personaje, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }

        try {
            this.service.save(personaje);
        }catch (ObjectAlreadyExistException e){
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El personaje " + personaje.getNombre() + " ha sido creado con éxito!");


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonaje(@PathVariable Long id,
                                             @Valid @RequestBody PersonajeDTO personaje, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }

        try {
            this.service.update(id, personaje);
        } catch (IdNotFoundException e) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El personaje " + personaje.getNombre() + " ha sido actualizado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
