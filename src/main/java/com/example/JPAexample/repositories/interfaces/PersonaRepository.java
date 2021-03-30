package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    List<Persona> findByNombreOrApellido(String nombre, String apellido);

    List<Persona> findByIdOrEdad(Integer id, Integer edad);
}
