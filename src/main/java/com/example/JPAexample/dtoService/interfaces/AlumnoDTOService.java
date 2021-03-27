package com.example.JPAexample.dtoService.interfaces;

import com.example.JPAexample.models.DTO.AlumnoDTO;

import java.util.List;

public interface AlumnoDTOService {
    AlumnoDTO saveAlumno(AlumnoDTO alumnoDTO);

    AlumnoDTO updateAlumno(int id, AlumnoDTO alumnoDTO);

    AlumnoDTO getAlumnoById(Integer id);

    List<AlumnoDTO> getAllAlumnos();

    boolean deleteAlumno(int id);
}
