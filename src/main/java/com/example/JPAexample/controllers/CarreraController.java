package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoServices.interfaces.CarreraDTOService;
import com.example.JPAexample.models.DTO.CarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CarreraDTO> getSingleCarrera(@PathVariable Integer id) {
        if (id > 0) {
            CarreraDTO result = _carreraService.getCarreraById(id);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    @PreAuthorize("hasAuthority('carrera:read')")
    public ResponseEntity<List<CarreraDTO>> getAllCarreras() {
        return ResponseEntity.ok(_carreraService.getAllCarreras());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('carrera:write')")
    public ResponseEntity<CarreraDTO> saveCarrera(@RequestBody CarreraDTO newCarrera) {
        if (!newCarrera.getCodigo().isBlank() && !newCarrera.getNombre().isBlank()) {
            return ResponseEntity.ok(_carreraService.saveCarrera(newCarrera));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('carrera:write')")
    public ResponseEntity<CarreraDTO> updateCarrera(@PathVariable Integer id, @RequestBody CarreraDTO updatedCarrera) {
        if (!updatedCarrera.getCodigo().isBlank() && !updatedCarrera.getNombre().isBlank() && id > 0) {
            return ResponseEntity.ok(_carreraService.updateCarrera(id, updatedCarrera));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('carrera:write')")
    public ResponseEntity<Boolean> deleteCarrera(@PathVariable int id) {
        if (id > 0) {
            return ResponseEntity.ok(_carreraService.deleteCarrera(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}


