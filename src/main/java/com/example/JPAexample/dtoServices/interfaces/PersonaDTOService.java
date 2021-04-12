package com.example.JPAexample.dtoServices.interfaces;

import com.example.JPAexample.models.DTO.PersonaDTO;

import java.util.List;

public interface PersonaDTOService {
    PersonaDTO savePersona(PersonaDTO personaDTO);

    PersonaDTO updatePersona(int id, PersonaDTO personaDTO);

    List<PersonaDTO> getPersonaByAny(String termino);

    PersonaDTO getPersonaById(int id);

    List<PersonaDTO> getAllPersonas();

    boolean deletePersona(int id);
}