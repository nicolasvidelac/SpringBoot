package com.example.JPAexample.dtoLayer;

import com.example.JPAexample.models.DTO.AlumnoDTO;

import java.util.List;

public interface AlumnoDTOService {
    AlumnoDTO saveAlumno(AlumnoDTO newAlumno);

    AlumnoDTO updateAlumno(int id, AlumnoDTO updatedAlumno);

    AlumnoDTO getAlumnoById(Integer id);

    List<AlumnoDTO> getAllAlumnos();

    boolean deleteAlumno(int id);
}
