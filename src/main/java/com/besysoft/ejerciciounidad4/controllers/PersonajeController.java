package com.besysoft.ejerciciounidad4.controllers;

import com.besysoft.ejerciciounidad4.domain.entity.Personaje;
import com.besysoft.ejerciciounidad4.services.interfaces.PersonajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private final PersonajeService service;

    public PersonajeController(PersonajeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Personaje>> findAll(@RequestParam(required = false) String nombre,
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
    public ResponseEntity<?> savePersonaje(@RequestBody Personaje personaje) {

        Map<String, Object> response = new HashMap<>();

        if (personaje.getNombre() == null || personaje.getNombre().isBlank()) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "El campo nombre no puede estar vacío");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (this.service.save(personaje) == null) {
            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "El personaje " + personaje.getNombre() + " ya existe");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El personaje " + personaje.getNombre() + " ha sido creado con éxito!");

        this.service.save(personaje);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonaje(@PathVariable Long id,
                                             @RequestBody Personaje personaje) {

        Personaje personajeUpdate = this.service.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (personajeUpdate == null) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "Error: no se pudo editar, el personaje ID: "
                    .concat(id.toString().concat(" no existe")));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (personaje.getNombre() == null || personaje.getNombre().isBlank()) {

            response.put("succes", Boolean.FALSE);
            response.put("mensaje", "El campo nombre no puede estar vacío");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        personajeUpdate.setNombre(personaje.getNombre());
        personajeUpdate.setEdad(personaje.getEdad());
        personajeUpdate.setPeso(personaje.getPeso());
        personajeUpdate.setHistoria(personaje.getHistoria());
        personajeUpdate.setPelicula(personaje.getPelicula());

        this.service.save(personajeUpdate);

        response.put("succes", Boolean.TRUE);
        response.put("mensaje", "¡El personaje " + personaje.getNombre() + " ha sido actualizado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
