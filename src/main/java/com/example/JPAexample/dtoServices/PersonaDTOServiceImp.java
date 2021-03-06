package com.example.JPAexample.dtoServices;

import com.example.JPAexample.dtoServices.interfaces.PersonaDTOService;
import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.models.Persona;
import com.example.JPAexample.services.interfaces.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaDTOServiceImp implements PersonaDTOService {

    public final ModelMapper _modelMapper;
    public final PersonaService _personaService;

    @Autowired
    public PersonaDTOServiceImp(ModelMapper modelMapper, PersonaService personaService) {
        _modelMapper = modelMapper;
        _personaService = personaService;
    }

    @Override
    public PersonaDTO savePersona(PersonaDTO personaDTO) {

        Persona entity = _modelMapper.map(personaDTO, Persona.class);
        entity.setId(null); //si es que viene con el id, funciona como un update

        entity = _personaService.savePersona(entity);

        return _modelMapper.map(entity, PersonaDTO.class);
    }

    @Override
    public PersonaDTO updatePersona(int id, PersonaDTO personaDTO) {

        Persona entity = _modelMapper.map(personaDTO, Persona.class);

        entity = _personaService.updatePersona(id, entity);

        return _modelMapper.map(entity, PersonaDTO.class);
    }

    @Override
    public List<PersonaDTO> getPersonaByAny(String termino) {

        List<Persona> personas = _personaService.getPersonaByAny(termino);
        List<PersonaDTO> entities = new ArrayList<>();

        for (Persona persona : personas) {
            entities.add(_modelMapper.map(persona, PersonaDTO.class));
        }
        return entities;
    }

    @Override
    public PersonaDTO getPersonaById(int id) {
        Persona result = _personaService.getPersonaById(id);
        return _modelMapper.map(result, PersonaDTO.class);
    }

    @Override
    public List<PersonaDTO> getAllPersonas() {

        List<Persona> personas = _personaService.getAllPersonas();
        List<PersonaDTO> entities = new ArrayList<>();

        for (Persona persona : personas) {
            entities.add(_modelMapper.map(persona, PersonaDTO.class));
        }
        return entities;
    }

    @Override
    public boolean deletePersona(int id) {

        return _personaService.deletePersona(id);

    }
}
