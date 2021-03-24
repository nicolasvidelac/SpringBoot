package com.example.JPAexample.services;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.repositories.CarreraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository _carreraRepository;

    @Autowired
    private ModelMapper _modelMapper;

    public CarreraDTO saveCarrera(Carrera newCarrera){

        Carrera entity = _carreraRepository.saveAndFlush(newCarrera);
        return _modelMapper.map(entity, CarreraDTO.class);

    }

    public CarreraDTO updateCarrera(int id, Carrera updateCarrera){

        Carrera entity = _carreraRepository.getOne(id);
        entity.setNombre(updateCarrera.getNombre());
        entity.setCodigo(updateCarrera.getCodigo());

        entity = _carreraRepository.saveAndFlush(entity);

        return _modelMapper.map(entity, CarreraDTO.class);
    }


    public CarreraDTO getCarreraById(int id){

        Carrera entity = _carreraRepository.getOne(id);
        return _modelMapper.map(entity, CarreraDTO.class);
    }

    public List<CarreraDTO> getAllCarreras(){

        List<Carrera> carreras = _carreraRepository.findAll();
        List<CarreraDTO> entities = new ArrayList<>();

        for (Carrera carrera: carreras) {
            entities.add(_modelMapper.map(carrera, CarreraDTO.class));
        }

        return entities;

    }

    public boolean deleteCarrera(int id) {
        _carreraRepository.deleteById(id);
        return true;
    }
}
