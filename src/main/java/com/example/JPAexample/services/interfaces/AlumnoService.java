package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.Alumno;

import java.util.List;

public interface AlumnoService {
    Alumno saveAlumno(Alumno newAlumno);

    Alumno updateAlumno(int id, Alumno updatedAlumno);

    Alumno getAlumnoById(Integer id);

    List<Alumno> getAllAlumnos();

    List<Alumno> getAlumnoByAny(String termino);

    boolean deleteAlumno(int id);
}
