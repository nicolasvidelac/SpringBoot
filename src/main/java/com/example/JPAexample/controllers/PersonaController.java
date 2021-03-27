package com.example.JPAexample.controllers;

import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.services.interfaces.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/personas")
public class PersonaController {

    private final PersonaService _personaService;

    @Autowired
    public PersonaController(PersonaService _personaService) {
        this._personaService = _personaService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persona:read')")
    public PersonaDTO getSinglePersona(@PathVariable int id) {
        return _personaService.getPersonaById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('persona:read')")
    public List<PersonaDTO> getAllPersonas() {
        return _personaService.getAllPersonas();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('persona:write')")
    public PersonaDTO savePersona(@RequestBody PersonaDTO newPersona) {
        return _personaService.savePersona(newPersona);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('persona:write')")
    public PersonaDTO updatePersona(@PathVariable int id, @RequestBody PersonaDTO updatedPersona) {
        return _personaService.updatePersona(id, updatedPersona);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persona:write')")
    public boolean deletePersona(@PathVariable int id) {
        return _personaService.deletePersona(id);
    }
}