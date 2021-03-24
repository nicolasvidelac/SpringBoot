package com.example.JPAexample.controllers;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/carreras")
public class CarreraController {

    private final CarreraService _carreraService;

    @Autowired
    public CarreraController(CarreraService _carreraService) {
        this._carreraService = _carreraService;
    }

    @GetMapping("/{id}")
    public CarreraDTO getSingleCarrera(@PathVariable int id){
        return _carreraService.getCarreraById(id);
    }

    @GetMapping
    public List<CarreraDTO> getAllCarreras(){
        return _carreraService.getAllCarreras();
    }

    @PostMapping
    public CarreraDTO saveCarrera(@RequestBody Carrera newCarrera){
        return _carreraService.saveCarrera(newCarrera);
    }

    @PutMapping("{id}")
    public CarreraDTO updateCarrera(@PathVariable int id, @RequestBody Carrera updatedCarrera){
        return _carreraService.updateCarrera(id, updatedCarrera);

    }

    @DeleteMapping("/{id}")
    public boolean deleteCarrera(@PathVariable int id){
        return _carreraService.deleteCarrera(id);
    }
}


