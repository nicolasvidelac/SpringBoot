package com.example.JPAexample.controllers.thymeleaf;


import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getAllAlumnos(Model model){

        model.addAttribute("message", "Esta es una lista de Alumnos sin orden");
        model.addAttribute("alumnos", _alumnoService.getAllAlumnos());

        return "sample_list";
    }

    @GetMapping("/sorted")
    public String sortAlumnos(Model model){
        TreeSet result = new TreeSet<Alumno>();
        result.addAll(_alumnoService.getAllAlumnos());

        model.addAttribute("message", "Esta es una lista de Alumnos ordenada por edad");
        model.addAttribute("alumnos", result);
        return "sample_list";
    }

    @GetMapping(value = "/{id}",  produces = "application/json")
    public String getAlumno(@PathVariable Integer id, Model model){
        model.addAttribute("alumnos", _alumnoService.getAlumnoById(id));
        return "sample_list";
    }
}

