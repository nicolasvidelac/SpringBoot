package com.example.JPAexample.dtoServices;

import com.example.JPAexample.dtoServices.interfaces.CarreraDTOService;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarreraDTOServiceImp implements CarreraDTOService {

    public final ModelMapper _modelMapper;
    public final CarreraService _carreraService;

    @Autowired
    public CarreraDTOServiceImp(ModelMapper modelMapper, CarreraService carreraService) {
        _modelMapper = modelMapper;
        _carreraService = carreraService;
    }

    @Override
    public CarreraDTO saveCarrera(CarreraDTO carreraDTO) {


        Carrera entity = _modelMapper.map(carreraDTO, Carrera.class);

        entity.setId(null); //si es que viene con el id, funciona como un update

        entity = _carreraService.saveCarrera(entity);

        return _modelMapper.map(entity, CarreraDTO.class);
    }

    @Override
    public CarreraDTO updateCarrera(int id, CarreraDTO carreraDTO) {

        Carrera carrera = _modelMapper.map(carreraDTO, Carrera.class);
        carrera = _carreraService.updateCarrera(id, carrera);

        return _modelMapper.map(carrera, CarreraDTO.class);
    }

    @Override
    public CarreraDTO getCarreraById(Integer id) {

        Carrera carrera = _carreraService.getCarreraById(id);
        return _modelMapper.map(carrera, CarreraDTO.class);
    }

    @Override
    public List<CarreraDTO> getAllCarreras() {

        List<Carrera> carreras = _carreraService.getAllCarreras();
        List<CarreraDTO> entities = new ArrayList<>();


        for (Carrera carrera : carreras) {
            entities.add(_modelMapper.map(carrera, CarreraDTO.class));
        }

        return entities;
    }

    @Override
    public List<CarreraDTO> getCarrerasByAny(String termino) {

        List<Carrera> carreras = _carreraService.getCarreraByAny(termino);;
        List<CarreraDTO> entities = new ArrayList<>();


        for (Carrera carrera : carreras) {
            entities.add(_modelMapper.map(carrera, CarreraDTO.class));
        }

        return entities;
    }

    @Override
    public boolean deleteCarrera(int id) {

        if (_carreraService.deleteCarrera(id)){
            return true;
        } else {
            return false;
        }
    }
}
