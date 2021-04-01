package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoServices.interfaces.PersonaDTOService;
import com.example.JPAexample.models.DTO.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/personas")
public class PersonaController {

    private final PersonaDTOService _personaService;

    @Autowired
    public PersonaController(PersonaDTOService _personaService) {
        this._personaService = _personaService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persona:read')")
    public ResponseEntity<PersonaDTO> getSinglePersona(@PathVariable int id) {
        if (id > 0) {
            return ResponseEntity.ok(_personaService.getPersonaById(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('persona:read')")
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        return ResponseEntity.ok(_personaService.getAllPersonas());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('persona:write')")
    public ResponseEntity<PersonaDTO> savePersona(@RequestBody PersonaDTO newPersona) {
        if (
                !newPersona.getApellido().isBlank() &&
                !newPersona.getNombre().isBlank() &&
                newPersona.getEdad() > 0
        ) {
            return ResponseEntity.ok( _personaService.savePersona(newPersona));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('persona:write')")
    public ResponseEntity<PersonaDTO> updatePersona(@PathVariable int id, @RequestBody PersonaDTO updatedPersona) {
        if (
                !updatedPersona.getApellido().isBlank() &&
                !updatedPersona.getNombre().isBlank() &&
                updatedPersona.getEdad() > 0
        ) {
            return ResponseEntity.ok(_personaService.updatePersona(id, updatedPersona));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persona:write')")
    public ResponseEntity<Boolean> deletePersona(@PathVariable int id) {
        if (id > 0){
            return ResponseEntity.ok(_personaService.deletePersona(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}