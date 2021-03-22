package com.example.JPAexample.services;
import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository _alumnoRepository;

    public Alumno saveAlumno(Alumno newAlumno){

        Alumno result;
        try {
            result = _alumnoRepository.saveAndFlush(newAlumno);
            return result;

        } catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "formato no valido"
            );
        }
    }

    public Optional getAlumnoById(Long id){

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

    public boolean deleteAlumno(Long id){

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

    private void throwException(String error){

    }

}