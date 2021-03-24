package com.example.JPAexample.services;
import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import com.example.JPAexample.repositories.AlumnoRepository;
import com.example.JPAexample.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository _alumnoRepository;

    @Autowired
    private CarreraRepository _carreraRepository;

    public Alumno saveAlumno(AlumnoDTO newAlumno){

        Alumno alumno = new Alumno();

        alumno.setNombre(newAlumno.getNombre());
        alumno.setApellido(newAlumno.getApellido());
        alumno.setEdad(newAlumno.getEdad());
        alumno.setEmail(newAlumno.getEmail());
        alumno.setLegajo(newAlumno.getLegajo());

        try {
            Carrera carrera = _carreraRepository.getOne(2);
            alumno.setCarrera(carrera);
            return _alumnoRepository.saveAndFlush(alumno);

        } catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "formato no valido"
            );
        }
    }

    public Alumno updateAlumno(Alumno updatedAlumno){

        try {

            Alumno alumno = _alumnoRepository.getOne(updatedAlumno.getId());

            alumno.setNombre(updatedAlumno.getNombre());
            alumno.setApellido(updatedAlumno.getApellido());
            alumno.setEdad(updatedAlumno.getEdad());
            alumno.setEmail(updatedAlumno.getEmail());
            alumno.setLegajo(updatedAlumno.getLegajo());

            return _alumnoRepository.saveAndFlush(alumno);

        } catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "formato no valido"
            );
        }
    }

    public Optional<Alumno> getAlumnoById(Integer id){

        Optional<Alumno> result = _alumnoRepository.findById( id);
        return result;
    }

    public List<Alumno> getAllAlumnos(){
        try {
            return _alumnoRepository.findAll();

        }catch (Exception e){

            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "error"
            );
        }
    }

    public boolean deleteAlumno(int id){

        try {
            _alumnoRepository.deleteById(id);
            return true;

        }catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "objeto no encontrado"
            );
        }
    }

}