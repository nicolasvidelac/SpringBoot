package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoServices.interfaces.AlumnoDTOService;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import com.example.JPAexample.models.DTO.CarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AlumnoDTO> getSingleAlumno(@PathVariable Integer id) {
        if (id > 0){
            AlumnoDTO result = _alumnoService.getAlumnoById(id);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('alumno:read')")
    public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
        return ResponseEntity.ok(_alumnoService.getAllAlumnos());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('alumno:write')")
    public ResponseEntity<AlumnoDTO> saveAlumno(@RequestBody AlumnoDTO newAlumno) {
        if (
                newAlumno.getCarrera_id() > 0 &&
                newAlumno.getEdad() > 0 &&
                !newAlumno.getApellido().isBlank() &&
                !newAlumno.getNombre().isBlank() &&
                !newAlumno.getEmail().isBlank() &&
                !newAlumno.getLegajo().isBlank()
        ) {
            AlumnoDTO result = _alumnoService.saveAlumno(newAlumno);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('alumno:write')")
    public ResponseEntity<AlumnoDTO> updateAlumno(@PathVariable int id, @RequestBody AlumnoDTO updateAlumno) {
        if (
                id > 0 &&
                updateAlumno.getCarrera_id() > 0 &&
                updateAlumno.getEdad() > 0 &&
                !updateAlumno.getApellido().isBlank() &&
                !updateAlumno.getNombre().isBlank() &&
                !updateAlumno.getEmail().isBlank() &&
                !updateAlumno.getLegajo().isBlank()
        ) {
            AlumnoDTO result = _alumnoService.updateAlumno(id, updateAlumno);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('alumno:write')")
    public ResponseEntity<Boolean> deleteAlumno(@PathVariable int id) {
        if (id > 0){
            return ResponseEntity.ok(_alumnoService.deleteAlumno(id));
        } else {
            return ResponseEntity.badRequest().build();
        }    }
}
