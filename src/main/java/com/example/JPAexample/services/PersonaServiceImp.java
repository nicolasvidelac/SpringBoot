package com.example.JPAexample.services;

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