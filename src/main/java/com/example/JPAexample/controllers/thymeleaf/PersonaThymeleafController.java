package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.dtoService.interfaces.PersonaDTOService;
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
            try {
                if (Integer.parseInt(termino) > 0) {
                    model.addAttribute("message", "Búsqueda de personas con '" + termino + "'");
                    model.addAttribute("personas", _personaService.getPersonaByIdOrEdad((Integer.parseInt(termino))));
                }
            } catch (NumberFormatException e) {
                model.addAttribute("message", "Búsqueda de personas con '" + termino + "'");
                model.addAttribute("personas", _personaService.getPersonaByNombreOrApellid(termino));
            }

        } else {
            TreeSet result = new TreeSet<PersonaDTO>();
            result.addAll(_personaService.getAllPersonas());
            model.addAttribute("message", "Esta es una lista de Personas ordenada por edad");
            model.addAttribute("personas", result);
        }
        return "sample_list";
    }
}