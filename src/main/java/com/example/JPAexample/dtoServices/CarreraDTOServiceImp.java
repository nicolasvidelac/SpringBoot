package com.example.JPAexample.dtoServices;

import com.example.JPAexample.dtoServices.interfaces.CarreraDTOService;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.models.DTO.UniversidadDTO;
import com.example.JPAexample.models.Universidad;
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

        Carrera carrera = _modelMapper.map(carreraDTO, Carrera.class);

        carrera.setId(null); //si es que viene con el id, funciona como un update

        carrera = _carreraService.saveCarrera(carrera);
        carreraDTO = _modelMapper.map(carrera, CarreraDTO.class);
        carreraDTO.setUniversidad(_modelMapper.map(carrera.getUniversidad(), UniversidadDTO.class));
        return carreraDTO;
    }

    @Override
    public CarreraDTO updateCarrera(int id, CarreraDTO carreraDTO) {

        Carrera carrera = _modelMapper.map(carreraDTO, Carrera.class);
        Universidad universidad = _modelMapper.map(carreraDTO.getUniversidad(), Universidad.class);

        carrera = _carreraService.updateCarrera(id, carrera);

        carreraDTO = _modelMapper.map(carrera, CarreraDTO.class);
        carreraDTO.setUniversidad(_modelMapper.map(carrera.getUniversidad(), UniversidadDTO.class));

        return carreraDTO;
    }

    @Override
    public CarreraDTO getCarreraById(Integer id) {

        Carrera carrera = _carreraService.getCarreraById(id);

        CarreraDTO result = _modelMapper.map(carrera, CarreraDTO.class);
        result.setUniversidad(_modelMapper.map(carrera.getUniversidad(), UniversidadDTO.class));
        return result;
    }

    @Override
    public List<CarreraDTO> getAllCarreras() {

        List<Carrera> carreras = _carreraService.getAllCarreras();
        List<CarreraDTO> entities = new ArrayList<>();


        for (Carrera carrera : carreras) {
            CarreraDTO carreraDTO = _modelMapper.map(carrera, CarreraDTO.class);
            carreraDTO.setUniversidad(_modelMapper.map(carrera.getUniversidad(), UniversidadDTO.class));
            entities.add(carreraDTO);
        }

        return entities;
    }

    @Override
    public List<CarreraDTO> getCarrerasByAny(String termino) {

        List<Carrera> carreras = _carreraService.getCarreraByAny(termino);
        List<CarreraDTO> entities = new ArrayList<>();

        for (Carrera carrera : carreras) {
            CarreraDTO carreraDTO = _modelMapper.map(carrera, CarreraDTO.class);
            carreraDTO.setUniversidad(_modelMapper.map(carrera.getUniversidad(), UniversidadDTO.class));
            entities.add(carreraDTO);
        }

        return entities;
    }

    @Override
    public boolean deleteCarrera(int id) {

        return _carreraService.deleteCarrera(id);
    }
}
