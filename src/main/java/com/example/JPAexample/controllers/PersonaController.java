package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoService.interfaces.PersonaDTOService;
import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.services.interfaces.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PersonaDTO getSinglePersona(@PathVariable int id) {
        return _personaService.getPersonaById(id);
    }

    @GetMapping
    public List<PersonaDTO> getAllPersonas() {
        return _personaService.getAllPersonas();
    }

    @PostMapping
    public PersonaDTO savePersona(@RequestBody PersonaDTO newPersona) {
        return _personaService.savePersona(newPersona);
    }

    @PutMapping("{id}")
    public PersonaDTO updatePersona(@PathVariable int id, @RequestBody PersonaDTO updatedPersona) {
        return _personaService.updatePersona(id, updatedPersona);
    }

    @DeleteMapping("/{id}")
    public boolean deletePersona(@PathVariable int id) {
        return _personaService.deletePersona(id);
    }
}