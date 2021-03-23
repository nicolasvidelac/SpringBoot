package com.example.JPAexample.services;

import com.example.JPAexample.models.Persona;
import com.example.JPAexample.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository _personaRepository;

    public Persona savePersona(Persona newPersona){

        Persona result;
        try {
            result = _personaRepository.saveAndFlush(newPersona);
            return result;

        } catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "formato no valido"
            );
        }
    }

    public Optional getPersonaById(Long id){

        Optional<Persona> result = _personaRepository.findById( id);
        return result;
    }

    public List<Persona> getAllPersonas(){
        try {
            return _personaRepository.findAll();

        }catch (Exception e){

            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "error"
            );
        }
    }

    public boolean deletePersona(Long id){

        try {
            _personaRepository.deleteById(id);
            return true;

        }catch (Exception e){
            System.out.println(e);

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "objeto no encontrado"
            );
        }
    }

}