package com.example.JPAexample.services;

import com.example.JPAexample.exceptions.MissingInfoException;
import com.example.JPAexample.exceptions.NotAcceptableException;
import com.example.JPAexample.exceptions.RecordNotFoundException;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.repositories.CarreraRepository;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarreraServiceImp implements CarreraService {

    @Autowired
    private CarreraRepository _carreraRepository;

    @Autowired
    private ModelMapper _modelMapper;

    public CarreraDTO saveCarrera(CarreraDTO newCarrera) {
        Carrera entity = _modelMapper.map(newCarrera, Carrera.class);

        entity.setId(null); //si es que viene con el id, funciona como un update

        try {
            entity = _carreraRepository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return _modelMapper.map(entity, CarreraDTO.class);

    }

    public CarreraDTO updateCarrera(int id, CarreraDTO updateCarrera) {

        Carrera entity = _carreraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Carrera con id '" + id + "' no existe"
        ));

        entity.setNombre(updateCarrera.getNombre());
        entity.setCodigo(updateCarrera.getCodigo());

        try {
            entity = _carreraRepository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return _modelMapper.map(entity, CarreraDTO.class);
    }


    public CarreraDTO getCarreraById(int id) {

        Carrera entity = _carreraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Carrera con id '" + id + "' no existe"
        ));
        return _modelMapper.map(entity, CarreraDTO.class);
    }

    public List<CarreraDTO> getAllCarreras() {

        List<Carrera> carreras = _carreraRepository.findAll();
        List<CarreraDTO> entities = new ArrayList<>();

        for (Carrera carrera : carreras) {
            entities.add(_modelMapper.map(carrera, CarreraDTO.class));
        }

        return entities;

    }

    public boolean deleteCarrera(int id) {
        try {
            _carreraRepository.deleteById(id);
            return true;

        } catch (EmptyResultDataAccessException e) {
            throw new RecordNotFoundException(
                    "Carrera con id '" + id + "' no existe"
            );
        } catch (DataIntegrityViolationException e){
            throw new NotAcceptableException(
                    "La Carrera no puede ser eliminada porque esta siendo utilizada por al menos un Alumno"
            );
        } catch (Exception e){
            throw e;
        }
    }
}
