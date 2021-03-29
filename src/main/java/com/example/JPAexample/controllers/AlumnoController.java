package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoService.interfaces.AlumnoDTOService;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/alumnos")
public class AlumnoController {

    private final AlumnoDTOService _alumnoService;

    @Autowired
    public AlumnoController(AlumnoDTOService _alumnoService) {
        this._alumnoService = _alumnoService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('alumno:read')")
    public List<AlumnoDTO> getSingleAlumno(@PathVariable Integer id) {
        return _alumnoService.getAlumnoByIdOrEdad(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('alumno:read')")
    public List<AlumnoDTO> getAllAlumnos() {
        return _alumnoService.getAllAlumnos();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('alumno:write')")
    public AlumnoDTO saveAlumno(@RequestBody AlumnoDTO newAlumno) {
        return _alumnoService.saveAlumno((newAlumno));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('alumno:write')")
    public AlumnoDTO updateAlumno(@PathVariable int id, @RequestBody AlumnoDTO updateAlumno) {
        return _alumnoService.updateAlumno(id, updateAlumno);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('alumno:write')")
    public boolean deleteAlumno(@PathVariable int id) {
        return _alumnoService.deleteAlumno(id);
    }
}
