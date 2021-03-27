package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoService.interfaces.CarreraDTOService;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/carreras")
public class CarreraController {

    private final CarreraDTOService _carreraService;

    @Autowired
    public CarreraController(CarreraDTOService _carreraService) {
        this._carreraService = _carreraService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('carrera:read')")
    public CarreraDTO getSingleCarrera(@PathVariable int id) {
        return _carreraService.getCarreraById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('carrera:read')")
    public List<CarreraDTO> getAllCarreras() {
        return _carreraService.getAllCarreras();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('carrera:write')")
    public CarreraDTO saveCarrera(@RequestBody CarreraDTO newCarrera) {
        return _carreraService.saveCarrera(newCarrera);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('carrera:write')")
    public CarreraDTO updateCarrera(@PathVariable int id, @RequestBody CarreraDTO updatedCarrera) {
        return _carreraService.updateCarrera(id, updatedCarrera);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('carrera:write')")
    public boolean deleteCarrera(@PathVariable int id) {
        return _carreraService.deleteCarrera(id);
    }
}


