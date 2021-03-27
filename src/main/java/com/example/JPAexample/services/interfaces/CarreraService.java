package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;

import java.util.List;

public interface CarreraService {
    Carrera saveCarrera(Carrera newCarrera);

    Carrera updateCarrera(int id, Carrera updateCarrera);

    Carrera getCarreraById(int id);

    List<Carrera> getAllCarreras();

    boolean deleteCarrera(int id);

}
