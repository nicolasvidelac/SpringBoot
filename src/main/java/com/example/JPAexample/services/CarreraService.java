package com.example.JPAexample.services;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {
    @Autowired
    private CarreraRepository _carreraRepository;

    public Carrera saveCarrera(Carrera newCarrera){

        Carrera result;
        try {
            result = _carreraRepository.saveAndFlush(newCarrera);
            return result;

        } catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "formato no valido"
            );
        }
    }

    public Optional getCarreraById(Long id){

        Optional<Carrera> result = _carreraRepository.findById( id);
        return result;
    }

    public List<Carrera> getAllCarreras(){
        try {

            return _carreraRepository.findAll();

        }catch (Exception e){

            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "error"
            );
        }
    }

    public boolean deleteCarrera(Long id){

        try {
            _carreraRepository.deleteById(id);
            return true;

        }catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "objeto no encontrado"
            );
        }
    }
}
