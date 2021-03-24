package com.example.JPAexample.controllers;

import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import com.example.JPAexample.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/alumnos")
public class AlumnoController {

    private final AlumnoService _alumnoService;

    @Autowired
    public AlumnoController(AlumnoService _alumnoService) {
        this._alumnoService = _alumnoService;
    }

    @GetMapping("/{id}")
    public Optional<Alumno> getSingleAlumno(@PathVariable Integer id){
        return _alumnoService.getAlumnoById(id);
    }

    @GetMapping
    public List<Alumno> getAllAlumnos(){
        return _alumnoService.getAllAlumnos();
    }

    @PostMapping
    public Alumno saveAlumno(@RequestBody AlumnoDTO newAlumno){
        Alumno result = _alumnoService.saveAlumno((newAlumno));
        return result;
    }

    @PutMapping
    public Alumno updateAlumno(@RequestBody Alumno updateAlumno){
        Alumno result = _alumnoService.updateAlumno((updateAlumno));
        return result;
    }

    @DeleteMapping("/{id}")
    public boolean deleteAlumno(@PathVariable int id){
        return _alumnoService.deleteAlumno(id);
    }
}
