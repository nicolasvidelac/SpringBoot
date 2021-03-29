package com.example.JPAexample.dtoService.interfaces;

import com.example.JPAexample.models.DTO.AlumnoDTO;

import java.util.List;

public interface AlumnoDTOService {
    AlumnoDTO saveAlumno(AlumnoDTO alumnoDTO);

    AlumnoDTO updateAlumno(int id, AlumnoDTO alumnoDTO);

    List<AlumnoDTO> getAlumnoByIdOrEdad(Integer numero);

    List<AlumnoDTO> getAllAlumnos();

    List<AlumnoDTO> getAlumnosByAny(String termino);

    boolean deleteAlumno(int id);
}
