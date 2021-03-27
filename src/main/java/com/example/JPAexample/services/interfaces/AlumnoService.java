package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.DTO.AlumnoDTO;

import java.util.List;

public interface AlumnoService {
    Alumno saveAlumno(Alumno newAlumno);

    Alumno updateAlumno(int id, Alumno updatedAlumno);

    Alumno getAlumnoById(Integer id);

    List<Alumno> getAllAlumnos();

    boolean deleteAlumno(int id);
}
