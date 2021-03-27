package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.models.DTO.AlumnoDTO;
import com.example.JPAexample.services.interfaces.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.TreeSet;

@Controller
@RequestMapping(path = "/alumnos")
public class AlumnoThymeleafController {

    private final AlumnoService _alumnoService;

    @Autowired
    public AlumnoThymeleafController(AlumnoService _alumnoService) {
        this._alumnoService = _alumnoService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('alumno:read')")
    public String getAlumnos(Model model, @RequestParam(required = false) Integer id) {
        if (id != null) {
            model.addAttribute("message", "Búsqueda de alumno");
            model.addAttribute("alumnos", _alumnoService.getAlumnoById(id));
        } else {
            TreeSet result = new TreeSet<AlumnoDTO>();
            result.addAll(_alumnoService.getAllAlumnos());

            model.addAttribute("message", "Esta es una lista de Alumnos ordenada por carrera");
            model.addAttribute("alumnos", result);
        }


        return "sample_list";
    }

}

