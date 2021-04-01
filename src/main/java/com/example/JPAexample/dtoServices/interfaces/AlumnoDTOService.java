package com.example.JPAexample.dtoServices.interfaces;

import com.example.JPAexample.models.DTO.AlumnoDTO;

import java.util.List;

public interface AlumnoDTOService {
    AlumnoDTO saveAlumno(AlumnoDTO alumnoDTO);

    AlumnoDTO updateAlumno(int id, AlumnoDTO alumnoDTO);

    List<AlumnoDTO> getAllAlumnos();

    List<AlumnoDTO> getAlumnosByAny(String termino);

    AlumnoDTO getAlumnoById(int id);

    boolean deleteAlumno(int id);
}
