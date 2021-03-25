package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.services.interfaces.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.TreeSet;

@Controller
@RequestMapping(path = "/personas")
public class PersonaThymeleafController {

    private final PersonaService _personaService;

    @Autowired
    public PersonaThymeleafController(PersonaService _personaService) {
        this._personaService = _personaService;
    }

    @GetMapping
    public String getAllPersonas(Model model){

        model.addAttribute("message", "Esta es una lista de Personas sin orden");
        model.addAttribute("personas", _personaService.getAllPersonas());

        return "sample_list";
    }

    @GetMapping("/sorted")
    public String sortPersonas(Model model){
        TreeSet result = new TreeSet<PersonaDTO>();
        result.addAll(_personaService.getAllPersonas());

        model.addAttribute("message", "Esta es una lista de Personas ordenada por edad");
        model.addAttribute("personas", result);
        return "sample_list";
    }

    @GetMapping(value = "/{id}",  produces = "application/json")
    public String getPersona(@PathVariable int id, Model model){
        model.addAttribute("message", "Búsqueda de persona");
        model.addAttribute("personas", _personaService.getPersonaById(id));
        return "sample_list";
    }
}