package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.dtoService.interfaces.AlumnoDTOService;
import com.example.JPAexample.models.DTO.AlumnoDTO;
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

    private final AlumnoDTOService _alumnoService;

    @Autowired
    public AlumnoThymeleafController(AlumnoDTOService _alumnoService) {
        this._alumnoService = _alumnoService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('alumno:read')")
    public String getAlumnos(Model model, @RequestParam(required = false) String termino) {

        if (termino != null){
            try {
                if (Integer.parseInt(termino) > 0){
                    model.addAttribute("message", "Búsqueda de alumnos con '" + termino + "'");
                    model.addAttribute("alumnos", _alumnoService.getAlumnoByIdOrEdad(Integer.parseInt(termino)));
                }
            } catch (NumberFormatException e){
                model.addAttribute("message", "Búsqueda de alumnos con '" + termino + "'");
                model.addAttribute("alumnos", _alumnoService.getAlumnosByAny(termino));
            }

        } else {
            TreeSet result = new TreeSet<AlumnoDTO>();
            result.addAll(_alumnoService.getAllAlumnos());
            model.addAttribute("message", "Esta es una lista de Alumnos ordenada por carrera");
            model.addAttribute("alumnos", result);
        }

        return "sample_list";
    }

}

