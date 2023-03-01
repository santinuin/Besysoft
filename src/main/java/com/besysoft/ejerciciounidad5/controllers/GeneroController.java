package com.besysoft.ejerciciounidad5.controllers;

import com.besysoft.ejerciciounidad5.domain.entity.Genero;
import com.besysoft.ejerciciounidad5.dto.GeneroDTO;
import com.besysoft.ejerciciounidad5.services.interfaces.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveGenero(@RequestBody GeneroDTO genero) {

        Map<String, Object> response = new HashMap<>();

        if (genero.getNombre() == null || genero.getNombre().isEmpty()) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "No es posible crear un genero sin nombre");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (this.service.save(genero) == null) {
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "El genero " + genero.getNombre() + " ya existe");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        this.service.save(genero);

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El genero " + genero.getNombre() + " ha sido creado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenero(@PathVariable Long id,
                                          @RequestBody GeneroDTO genero) {

        Map<String, Object> response = new HashMap<>();

        if (this.service.findById(id) == null) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "Error: no se pudo editar, el genero ID: "
                    .concat(id.toString().concat(" no existe.")));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (genero.getNombre() == null || genero.getNombre().isEmpty()) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "El campo nombre no puede estar vacío");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        this.service.update(id, genero);

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El genero " + genero.getNombre() + " ha sido modificado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
