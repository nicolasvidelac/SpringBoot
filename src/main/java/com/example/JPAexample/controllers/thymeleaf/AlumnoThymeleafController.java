package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.dtoService.interfaces.AlumnoDTOService;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.TreeSet;

@Controller
@RequestMapping(path = "/alumnos")
public class AlumnoThymeleafController {

    private final AlumnoDTOService _alumnoService;

    @Autowired
    public AlumnoThymeleafController(AlumnoDTOService _alumnoService) {
        this._alumnoService = _alumnoService;
    }

    @GetMapping
    public String getAlumnos(Model model, @RequestParam(required = false) Integer id) {
        if (id != null) {
            model.addAttribute("message", "Búsqueda de alumno");
            model.addAttribute("alumnos", _alumnoService.getAlumnoById(id));
        } else {
            model.addAttribute("message", "Esta es una lista de Alumnos sin orden");
            model.addAttribute("alumnos", _alumnoService.getAllAlumnos());
        }


        return "sample_list";
    }

    @GetMapping("/sorted")
    public String sortAlumnos(Model model) {
        TreeSet result = new TreeSet<AlumnoDTO>();
        result.addAll(_alumnoService.getAllAlumnos());

        model.addAttribute("message", "Esta es una lista de Alumnos ordenada por carrera");
        model.addAttribute("alumnos", result);
        return "sample_list";
    }

}

