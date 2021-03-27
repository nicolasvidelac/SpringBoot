package com.example.JPAexample.dtoService.interfaces;

import com.example.JPAexample.models.DTO.CarreraDTO;

import java.util.List;

public interface CarreraDTOService {
    CarreraDTO saveCarrera(CarreraDTO carreraDTO);

    CarreraDTO updateCarrera(int id, CarreraDTO carreraDTO);

    CarreraDTO getCarreraById(Integer id);

    List<CarreraDTO> getAllCarreras();

    boolean deleteCarrera(int id);
}
