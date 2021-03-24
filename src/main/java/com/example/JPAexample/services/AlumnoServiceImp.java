package com.example.JPAexample.services;

import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import com.example.JPAexample.repositories.AlumnoRepository;
import com.example.JPAexample.repositories.CarreraRepository;
import com.example.JPAexample.services.interfaces.AlumnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoServiceImp implements AlumnoService {

    @Autowired
    private AlumnoRepository _alumnoRepository;

    @Autowired
    private CarreraRepository _carreraRepository;

    @Autowired
    private ModelMapper _modelMapper;

    public AlumnoDTO saveAlumno(AlumnoDTO newAlumno){

        Alumno alumno = _modelMapper.map(newAlumno, Alumno.class);

        Carrera carrera = _carreraRepository.getOne(newAlumno.getCarrera_id());
        alumno.setCarrera(new Carrera(carrera.getId(), carrera.getNombre(), carrera.getCodigo()));

        alumno = _alumnoRepository.saveAndFlush(alumno);

        AlumnoDTO alumnoDTO = _modelMapper.map(alumno, AlumnoDTO.class);

        alumnoDTO.setCarrera_nombre(alumno.getCarrera().getNombre());
        alumnoDTO.setCarrera_codigo(alumno.getCarrera().getCodigo());
        alumnoDTO.setCarrera_id(alumno.getCarrera().getId());
        return alumnoDTO;
    }

    public AlumnoDTO updateAlumno(int id, AlumnoDTO updatedAlumno){

        Alumno alumno = _alumnoRepository.getOne(id);

        alumno.setNombre(updatedAlumno.getNombre());
        alumno.setApellido(updatedAlumno.getApellido());
        alumno.setEdad(updatedAlumno.getEdad());
        alumno.setEmail(updatedAlumno.getEmail());
        alumno.setLegajo(updatedAlumno.getLegajo());

        if (updatedAlumno.getCarrera_id() != alumno.getCarrera().getId()){
            Carrera carrera = _carreraRepository.getOne(updatedAlumno.getCarrera_id());
            alumno.setCarrera(new Carrera(carrera.getId(), carrera.getNombre(), carrera.getCodigo()));
        }

        alumno = _alumnoRepository.saveAndFlush(alumno);

        AlumnoDTO alumnoDTO = _modelMapper.map(alumno, AlumnoDTO.class);

        alumnoDTO.setCarrera_nombre(alumno.getCarrera().getNombre());
        alumnoDTO.setCarrera_codigo(alumno.getCarrera().getCodigo());
        alumnoDTO.setCarrera_id(alumno.getCarrera().getId());
        return alumnoDTO;
    }

    public AlumnoDTO getAlumnoById(Integer id){

        Alumno alumno = _alumnoRepository.getOne(id);

        AlumnoDTO alumnoDTO = _modelMapper.map(alumno, AlumnoDTO.class);

        alumnoDTO.setCarrera_nombre(alumno.getCarrera().getNombre());
        alumnoDTO.setCarrera_codigo(alumno.getCarrera().getCodigo());
        alumnoDTO.setCarrera_id(alumno.getCarrera().getId());

        return alumnoDTO;
    }

    public List<AlumnoDTO> getAllAlumnos(){

            List<Alumno> alumnos = _alumnoRepository.findAll();
            List<AlumnoDTO> result = new ArrayList<>();

            for (Alumno entity: alumnos) {
                AlumnoDTO alumnoDTO = _modelMapper.map(entity, AlumnoDTO.class);

                alumnoDTO.setCarrera_nombre(entity.getCarrera().getNombre());
                alumnoDTO.setCarrera_codigo(entity.getCarrera().getCodigo());
                alumnoDTO.setCarrera_id(entity.getCarrera().getId());
                result.add(alumnoDTO);
            }
            return result;
    }

    public boolean deleteAlumno(int id) {
        _alumnoRepository.deleteById(id);
        return true;
    }
}