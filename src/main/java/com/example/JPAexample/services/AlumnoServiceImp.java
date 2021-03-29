package com.example.JPAexample.services;

import com.example.JPAexample.exceptions.MissingInfoException;
import com.example.JPAexample.exceptions.RecordNotFoundException;
import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.repositories.interfaces.AlumnoRepository;
import com.example.JPAexample.repositories.interfaces.CarreraRepository;
import com.example.JPAexample.services.interfaces.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImp implements AlumnoService {

    @Autowired
    private AlumnoRepository _alumnoRepository;

    @Autowired
    private CarreraRepository _carreraRepository;



    public Alumno saveAlumno(Alumno newAlumno) {

        Carrera carrera = _carreraRepository.findById(newAlumno.getCarrera().getId()).orElseThrow(() ->
                new RecordNotFoundException("Carrera con id '" + newAlumno.getCarrera().getId() + "' no existe")
        );

        Alumno alumno = newAlumno;
        alumno.setCarrera(new Carrera(carrera.getId(), carrera.getNombre(), carrera.getCodigo()));

        try {
            alumno = _alumnoRepository.saveAndFlush(alumno);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return alumno;
    }

    public Alumno updateAlumno(int id, Alumno updatedAlumno) {

        Alumno alumno = _alumnoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Alumno con id '" + id + "' no existe"
        ));

        if (updatedAlumno.getCarrera().getId() != alumno.getCarrera().getId()) { //Si modifico la carrera, ejecuto esto
            Carrera carrera = _carreraRepository.findById(updatedAlumno.getCarrera().getId()).orElseThrow(() ->
                    new RecordNotFoundException("Carrera con id '" + updatedAlumno.getCarrera().getId() + "' no existe")
            );
            alumno.setCarrera(new Carrera(carrera.getId(), carrera.getNombre(), carrera.getCodigo()));
        }

        try {
            alumno = _alumnoRepository.saveAndFlush(alumno);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return alumno;
    }

    public List<Alumno> getAlumnoByIdOrEdad(Integer numero) {

        List<Alumno> alumno = _alumnoRepository.findByEdadOrId(numero, numero);
        return alumno;
    }

    public List<Alumno> getAllAlumnos() {

        List<Alumno> alumnos = _alumnoRepository.findAll();

        return alumnos;
    }

    @Override
    public List<Alumno> getAlumnoByAny(String termino) {
        return _alumnoRepository.findByLegajoOrEmailOrNombreOrApellido(termino, termino, termino, termino);
    }

    public boolean deleteAlumno(int id) {

        try {
            _alumnoRepository.deleteById(id);
            return true;

        } catch (EmptyResultDataAccessException e) {
            throw new RecordNotFoundException(
                    "Alumno con id '" + id + "' no existe"
            );
        } catch (Exception e) {
            throw e;
        }
    }
}