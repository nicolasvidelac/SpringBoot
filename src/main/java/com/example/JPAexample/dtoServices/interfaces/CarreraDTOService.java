package com.example.JPAexample.dtoServices.interfaces;

import com.example.JPAexample.models.DTO.CarreraDTO;

import java.util.List;

public interface CarreraDTOService {
    CarreraDTO saveCarrera(CarreraDTO carreraDTO);

    CarreraDTO updateCarrera(int id, CarreraDTO carreraDTO);

    CarreraDTO getCarreraById(Integer numero);

    List<CarreraDTO> getAllCarreras();

    List<CarreraDTO> getCarrerasByAny(String termino);

    boolean deleteCarrera(int id);
}
