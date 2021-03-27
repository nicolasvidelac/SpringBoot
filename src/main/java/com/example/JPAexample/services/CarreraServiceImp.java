package com.example.JPAexample.services;

import com.example.JPAexample.exceptions.MissingInfoException;
import com.example.JPAexample.exceptions.RecordNotFoundException;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.repositories.CarreraRepository;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImp implements CarreraService {

    @Autowired
    private CarreraRepository _carreraRepository;

    @Autowired
    private ModelMapper _modelMapper;

    public Carrera saveCarrera(Carrera newCarrera) {

        try {
            newCarrera = _carreraRepository.saveAndFlush(newCarrera);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return newCarrera;

    }

    public Carrera updateCarrera(int id, Carrera updateCarrera) {

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

        return entity;
    }


    public Carrera getCarreraById(int id) {

        Carrera entity = _carreraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Carrera con id '" + id + "' no existe"
        ));
        return entity;
    }

    public List<Carrera> getAllCarreras() {

        List<Carrera> carreras = _carreraRepository.findAll();
        return carreras;
    }

    public boolean deleteCarrera(int id) {
        try {
            _carreraRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RecordNotFoundException(
                    "Carrera con id '" + id + "' no existe"
            );
        }

    }
}
