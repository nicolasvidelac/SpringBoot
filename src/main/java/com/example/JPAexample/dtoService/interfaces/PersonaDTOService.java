package com.example.JPAexample.dtoService.interfaces;

import com.example.JPAexample.models.DTO.PersonaDTO;

import java.util.List;

public interface PersonaDTOService {
    PersonaDTO savePersona(PersonaDTO personaDTO);

    PersonaDTO updatePersona(int id, PersonaDTO personaDTO);

    List<PersonaDTO> getPersonaByIdOrEdad(Integer numero);

    List<PersonaDTO> getPersonaByNombreOrApellid(String termino);

    List<PersonaDTO> getAllPersonas();

    boolean deletePersona(int id);
}