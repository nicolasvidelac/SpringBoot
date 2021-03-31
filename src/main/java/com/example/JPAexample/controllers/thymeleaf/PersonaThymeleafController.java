package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.dtoServices.interfaces.PersonaDTOService;
import com.example.JPAexample.models.DTO.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.TreeSet;

@Controller
@RequestMapping(path = "/personas")
public class PersonaThymeleafController {

    private final PersonaDTOService _personaService;

    @Autowired
    public PersonaThymeleafController(PersonaDTOService _personaService) {
        this._personaService = _personaService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('persona:read')")
    public String getPersonas(Model model, @RequestParam(required = false) String termino) {

        if (termino != null) {
                model.addAttribute("message", "Búsqueda de personas con '" + termino + "'");
                model.addAttribute("personas", _personaService.getPersonaByAny(termino));
        } else {
            TreeSet<PersonaDTO> result = new TreeSet<PersonaDTO>(_personaService.getAllPersonas());
            model.addAttribute("message", "Esta es una lista de Personas ordenada por edad");
            model.addAttribute("personas", result);
        }
        return "list_personas";
    }
}