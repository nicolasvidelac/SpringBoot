package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.DTO.PersonaDTO;
import com.example.JPAexample.models.Persona;

import java.util.List;

public interface PersonaService {
    Persona savePersona(Persona newPersona);

    Persona updatePersona(int id, Persona updatedPersona);

    Persona getPersonaById(int id);

    List<Persona> getAllPersonas();

    boolean deletePersona(int id);
}
