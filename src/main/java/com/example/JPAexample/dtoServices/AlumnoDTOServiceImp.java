package com.example.JPAexample.dtoServices;

import com.example.JPAexample.dtoServices.interfaces.AlumnoDTOService;
import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.AlumnoDTO;
import com.example.JPAexample.services.interfaces.AlumnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoDTOServiceImp implements AlumnoDTOService {

    private final AlumnoService _alumnoService;
    private final ModelMapper _modelMapper;

    @Autowired
    public AlumnoDTOServiceImp(AlumnoService alumnoService, ModelMapper modelMapper) {
        _alumnoService = alumnoService;
        _modelMapper = modelMapper;
    }

    @Override
    public AlumnoDTO saveAlumno(AlumnoDTO alumnoDTO) {

        Alumno alumno = _modelMapper.map(alumnoDTO, Alumno.class);

        alumno.setCarrera(new Carrera(alumnoDTO.getCarrera_id() , "", ""));

        alumno = _alumnoService.saveAlumno(alumno);

        alumnoDTO = _modelMapper.map(alumno, AlumnoDTO.class);

        alumnoDTO.setCarrera_nombre(alumno.getCarrera().getNombre());
        alumnoDTO.setCarrera_codigo(alumno.getCarrera().getCodigo());
        alumnoDTO.setCarrera_id(alumno.getCarrera().getId());

        return alumnoDTO;
    }

    @Override
    public AlumnoDTO updateAlumno(int id, AlumnoDTO alumnoDTO) {

        Alumno alumno = _modelMapper.map(alumnoDTO, Alumno.class);
        alumno.setCarrera(new Carrera(alumnoDTO.getCarrera_id() , "", ""));

        alumno = _alumnoService.updateAlumno(id, alumno);

        alumnoDTO = _modelMapper.map(alumno, AlumnoDTO.class);

        alumnoDTO.setCarrera_nombre(alumno.getCarrera().getNombre());
        alumnoDTO.setCarrera_codigo(alumno.getCarrera().getCodigo());
        alumnoDTO.setCarrera_id(alumno.getCarrera().getId());

        return alumnoDTO;
    }

    @Override
    public AlumnoDTO getAlumnoById(int numero) {

        Alumno alumno = _alumnoService.getAlumnoById(numero);

        return _modelMapper.map(alumno, AlumnoDTO.class);
    }

    @Override
    public List<AlumnoDTO> getAllAlumnos() {

        List<Alumno> alumnos = _alumnoService.getAllAlumnos();
        List<AlumnoDTO> result = new ArrayList<>();

        for (Alumno entity : alumnos) {
            AlumnoDTO alumnoDTO = _modelMapper.map(entity, AlumnoDTO.class);

            alumnoDTO.setCarrera_nombre(entity.getCarrera().getNombre());
            alumnoDTO.setCarrera_codigo(entity.getCarrera().getCodigo());
            alumnoDTO.setCarrera_id(entity.getCarrera().getId());
            result.add(alumnoDTO);
        }

        return result;
    }

    @Override
    public List<AlumnoDTO> getAlumnosByAny(String termino) {

        List<Alumno> alumnos = _alumnoService.getAlumnoByAny(termino);
        List<AlumnoDTO> result = new ArrayList<>();

        for (Alumno entity : alumnos) {
            AlumnoDTO alumnoDTO = _modelMapper.map(entity, AlumnoDTO.class);

            alumnoDTO.setCarrera_nombre(entity.getCarrera().getNombre());
            alumnoDTO.setCarrera_codigo(entity.getCarrera().getCodigo());
            alumnoDTO.setCarrera_id(entity.getCarrera().getId());
            result.add(alumnoDTO);
        }

        return result;
    }

    @Override
    public boolean deleteAlumno(int id) {

        if (_alumnoService.deleteAlumno(id)){
            return true;
        }else {
            return false;

        }
    }
}
