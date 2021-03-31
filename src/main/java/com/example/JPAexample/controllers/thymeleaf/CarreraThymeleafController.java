package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.dtoServices.interfaces.CarreraDTOService;
import com.example.JPAexample.models.DTO.CarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.TreeSet;

@Controller
@RequestMapping(path = "/carreras")
public class CarreraThymeleafController {

    private final CarreraDTOService _carreraService;

    @Autowired
    public CarreraThymeleafController(CarreraDTOService _carreraService) {
        this._carreraService = _carreraService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('carrera:read')")
    public String getCarreras(Model model, @RequestParam(required = false) String termino) {

        if (termino != null){
            model.addAttribute("message", "Búsqueda de carreras con '" + termino + "'");
            model.addAttribute("carreras", _carreraService.getCarrerasByAny(termino));
        } else {
            TreeSet<CarreraDTO> result = new TreeSet<CarreraDTO>(_carreraService.getAllCarreras());
            model.addAttribute("message", "Esta es una lista de Carreras ordenada por carrera");
            model.addAttribute("carreras", result);
        }

        return "list_carreras";
    }
}

