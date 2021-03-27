package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.dtoService.interfaces.CarreraDTOService;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getCarreras(Model model, @RequestParam(required = false) Integer id) {

        if (id != null) {
            model.addAttribute("message", "Búsqueda de carrera");
            model.addAttribute("carreras", _carreraService.getCarreraById(id));
        } else {
            model.addAttribute("message", "Esta es una lista de Carreras sin orden");
            model.addAttribute("carreras", _carreraService.getAllCarreras());
        }


        return "sample_list";
    }

    @GetMapping("/sorted")
    public String sortCarreras(Model model) {
        TreeSet result = new TreeSet<CarreraDTO>();
        result.addAll(_carreraService.getAllCarreras());

        model.addAttribute("message", "Esta es una lista de Carreras ordenada");
        model.addAttribute("carreras", result);
        return "sample_list";
    }
}

