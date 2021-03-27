package com.example.JPAexample.dtoService.interfaces;

import com.example.JPAexample.models.DTO.PersonaDTO;

import java.util.List;

public interface PersonaDTOService {
    PersonaDTO savePersona(PersonaDTO personaDTO);

    PersonaDTO updatePersona(int id, PersonaDTO personaDTO);

    PersonaDTO getPersonaById(Integer id);

    List<PersonaDTO> getAllPersonas();

    boolean deletePersona(int id);
}