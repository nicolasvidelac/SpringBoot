package com.example.JPAexample.services;

import com.example.JPAexample.exceptions.MissingInfoException;
import com.example.JPAexample.exceptions.RecordNotFoundException;
import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.models.Persona;
import com.example.JPAexample.repositories.PersonaRepository;
import com.example.JPAexample.services.interfaces.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository _personaRepository;

    public Persona savePersona(Persona newPersona) {

        newPersona.setId(null); //si es que viene con el id, funciona como un update

        try {
            newPersona = _personaRepository.saveAndFlush(newPersona);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return newPersona;
    }

    public Persona updatePersona(int id, Persona updatedPersona) {

        Persona entity = _personaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Persona con id '" + id + "' no existe"
        ));

        entity.setEdad(updatedPersona.getEdad());
        entity.setApellido(updatedPersona.getApellido());
        entity.setNombre(updatedPersona.getNombre());

        try {
            entity = _personaRepository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return entity;
    }

    public Persona getPersonaById(int id) {

        Persona entity = _personaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Persona con id '" + id + "' no existe"
        ));

        return entity;
    }

    public List<Persona> getAllPersonas() {
        List<Persona> personas = _personaRepository.findAll();

        return personas;
    }

    public boolean deletePersona(int id) {

        try {
            _personaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RecordNotFoundException(
                    "Persona con id '" + id + "' no existe"
            );
        }
    }

}