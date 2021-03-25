package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.DTO.PersonaDTO;

import java.util.List;

public interface PersonaService {
    PersonaDTO savePersona(PersonaDTO newPersona);

    PersonaDTO updatePersona(int id, PersonaDTO updatedPersona);

    PersonaDTO getPersonaById(int id);

    List<PersonaDTO> getAllPersonas();

    boolean deletePersona(int id);
}
