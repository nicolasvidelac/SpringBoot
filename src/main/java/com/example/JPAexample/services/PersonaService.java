package com.example.JPAexample.services;

import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.models.Persona;
import com.example.JPAexample.repositories.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository _personaRepository;

    @Autowired
    private ModelMapper _modelMapper;

    public PersonaDTO savePersona(Persona newPersona){
        Persona entity = _personaRepository.saveAndFlush(newPersona);
        return _modelMapper.map(entity, PersonaDTO.class);
    }

    public PersonaDTO updatePersona(int id, Persona updatedPersona){

        Persona result = _personaRepository.getOne(id);

        result.setEdad(updatedPersona.getEdad());
        result.setApellido(updatedPersona.getApellido());
        result.setNombre(updatedPersona.getNombre());

        result = _personaRepository.saveAndFlush(result);
        return _modelMapper.map(result, PersonaDTO.class);
    }

    public PersonaDTO getPersonaById(int id){

        Persona result = _personaRepository.getOne(id);
        return _modelMapper.map(result, PersonaDTO.class);
    }

    public List<PersonaDTO> getAllPersonas(){
        List<Persona> personas = _personaRepository.findAll();
        List<PersonaDTO> entities = new ArrayList<>();

        for(Persona persona : personas){
            entities.add(_modelMapper.map(persona, PersonaDTO.class));
        }

        return entities;
    }

    public boolean deletePersona(int id){
        _personaRepository.deleteById(id);
        return true;
    }

}