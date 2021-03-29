package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.DTO.AlumnoDTO;

import java.util.List;

public interface AlumnoService {
    Alumno saveAlumno(Alumno newAlumno);

    Alumno updateAlumno(int id, Alumno updatedAlumno);

    List<Alumno> getAlumnoByIdOrEdad(Integer numero);

    List<Alumno> getAllAlumnos();

    List<Alumno> getAlumnoByAny(String termino);

    boolean deleteAlumno(int id);
}
