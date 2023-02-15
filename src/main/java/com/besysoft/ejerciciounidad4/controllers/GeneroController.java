package com.besysoft.ejerciciounidad4.controllers;

import com.besysoft.ejerciciounidad4.domain.entity.Genero;
import com.besysoft.ejerciciounidad4.services.interfaces.GeneroService;
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
    public ResponseEntity<List<Genero>> findAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveGenero(@RequestBody Genero genero) {

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

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El genero " + genero.getNombre() + " ha sido creado con éxito!");

        this.service.save(genero);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenero(@PathVariable Long id,
                                          @RequestBody Genero genero) {

        Genero generoUpdate = this.service.findById(id);

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

        generoUpdate.setNombre(genero.getNombre());
        generoUpdate.setPeliculas(genero.getPeliculas());

        this.service.save(generoUpdate);

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El genero " + genero.getNombre() + " ha sido modificado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
