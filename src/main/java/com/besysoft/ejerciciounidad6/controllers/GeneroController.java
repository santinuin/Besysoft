package com.besysoft.ejerciciounidad6.controllers;

import com.besysoft.ejerciciounidad6.dto.GeneroDTO;
import com.besysoft.ejerciciounidad6.excepciones.IdNotFoundException;
import com.besysoft.ejerciciounidad6.excepciones.ObjectAlreadyExistException;
import com.besysoft.ejerciciounidad6.services.interfaces.GeneroService;
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
@RequestMapping("/genero")
public class GeneroController {

    private static final Logger logger = LoggerFactory.getLogger(GeneroController.class);

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveGenero(@Valid @RequestBody GeneroDTO genero, BindingResult result) {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }

        try{
            this.service.save(genero);
        } catch (ObjectAlreadyExistException e) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El genero " + genero.getNombre() + " ha sido creado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenero(@PathVariable Long id,
                                          @Valid @RequestBody GeneroDTO genero, BindingResult result) throws IdNotFoundException {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }


        try {
            this.service.update(id, genero);
        } catch (IdNotFoundException e) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "Error: no se pudo editar, el genero ID: "
                    .concat(id.toString().concat(" no existe.")));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El genero " + genero.getNombre() + " ha sido modificado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
