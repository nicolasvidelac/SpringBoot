package com.example.JPAexample.services.interfaces;

import com.example.JPAexample.models.Persona;

import java.util.List;

public interface PersonaService {
    Persona savePersona(Persona newPersona);

    Persona updatePersona(int id, Persona updatedPersona);

    List<Persona> getPersonaByAny(String termino);

    Persona getById(int id);

    List<Persona> getAllPersonas();

    boolean deletePersona(int id);
}
