package com.example.JPAexample.controllers;

import com.example.JPAexample.models.Persona;
import com.example.JPAexample.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/personas")
public class PersonaController {

    private final PersonaService _personaService;

    @Autowired
    public PersonaController(PersonaService _personaService) {
        this._personaService = _personaService;
    }

    @GetMapping("/{id}")
    public Optional<Persona> getSinglePersona(@PathVariable Long id){
        return _personaService.getPersonaById(id);
    }

    @GetMapping
    public List<Persona> getAllPersonas(){
        return _personaService.getAllPersonas();
    }

    @PostMapping
    public Persona savePersona(@RequestBody Persona newPersona){

        Persona result = _personaService.savePersona((newPersona));
        return result;

    }

    @DeleteMapping("/{id}")
    public boolean deletePersona(@PathVariable Long id){
        return _personaService.deletePersona(id);
    }
}