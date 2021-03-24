package com.example.JPAexample.controllers.thymeleaf;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.TreeSet;

@Controller
@RequestMapping(path = "/carreras")
public class CarreraThymeleafController {

    private final CarreraService _carreraService;

    @Autowired
    public CarreraThymeleafController(CarreraService _carreraService) {
        this._carreraService = _carreraService;
    }

    @GetMapping
    public String getAllCarreras(Model model){

        model.addAttribute("message", "Esta es una lista de Carreras sin orden");
        model.addAttribute("carreras", _carreraService.getAllCarreras());

        return "sample_list";
    }

    @GetMapping("/sorted")
    public String sortCarreras(Model model){
        TreeSet result = new TreeSet<Carrera>();
        result.addAll(_carreraService.getAllCarreras());

        model.addAttribute("message", "Esta es una lista de Carreras ordenada");
        model.addAttribute("carreras", result);
        return "sample_list";
    }

    @GetMapping(value = "/{id}",  produces = "application/json")
    public String getCarrera(@PathVariable int id, Model model){
        model.addAttribute("carreras", _carreraService.getCarreraById(id));
        return "sample_list";
    }
}

