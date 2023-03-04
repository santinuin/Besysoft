package com.besysoft.ejerciciounidad6.controllers;

import com.besysoft.ejerciciounidad6.dto.GeneroDTO;
import com.besysoft.ejerciciounidad6.exceptions.IdNotFoundException;
import com.besysoft.ejerciciounidad6.exceptions.ObjectAlreadyExistException;
import com.besysoft.ejerciciounidad6.services.interfaces.GeneroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/genero")
@Api(value = "GeneroController", tags = "Acciones disponibles para Genero")
public class GeneroController {

    private static final Logger logger = LoggerFactory.getLogger(GeneroController.class);

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Devuelve todos los generos")
    public ResponseEntity<List<GeneroDTO>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Guarda un nuevo genero")
    public ResponseEntity<?> saveGenero(@Valid @RequestBody GeneroDTO genero) {

        Map<String, Object> response = new HashMap<>();

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
    @ApiOperation(value = "Modifica un genero")
    public ResponseEntity<?> updateGenero(@PathVariable Long id,
                                          @Valid @RequestBody GeneroDTO genero) throws IdNotFoundException {

        Map<String, Object> response = new HashMap<>();

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
