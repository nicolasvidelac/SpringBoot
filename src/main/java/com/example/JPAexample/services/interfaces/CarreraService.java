package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;

import java.util.List;

public interface CarreraService {
    CarreraDTO saveCarrera(Carrera newCarrera);
    CarreraDTO updateCarrera(int id, Carrera updateCarrera);
    CarreraDTO getCarreraById(int id);
    List<CarreraDTO> getAllCarreras();
    boolean deleteCarrera(int id);

}
