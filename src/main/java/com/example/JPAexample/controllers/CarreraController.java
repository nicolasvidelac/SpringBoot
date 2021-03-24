package com.example.JPAexample.controllers;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/carreras")
public class CarreraController {

    private final CarreraService _CarreraService;

    @Autowired
    public CarreraController(CarreraService _CarreraService) {
        this._CarreraService = _CarreraService;
    }

    @GetMapping("/{id}")
    public Optional<Carrera> getSingleCarrera(@PathVariable int id){
        return _CarreraService.getCarreraById(id);
    }

    @GetMapping
    public List<Carrera> getAllCarreras(){
        return _CarreraService.getAllCarreras();
    }

    @PostMapping
    public Carrera saveCarrera(@RequestBody Carrera newCarrera){
        Carrera result = _CarreraService.saveCarrera((newCarrera));
        return result;
    }

    @PutMapping
    public Carrera updateCarrera(@RequestBody Carrera updatedCarrera){
        Carrera result = _CarreraService.updateCarrera((updatedCarrera));
        return result;

    }

    @DeleteMapping("/{id}")
    public boolean deleteCarrera(@PathVariable int id){
        return _CarreraService.deleteCarrera(id);
    }
}


