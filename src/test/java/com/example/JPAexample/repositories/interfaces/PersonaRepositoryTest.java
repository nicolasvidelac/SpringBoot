package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.Persona;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@DataJpaTest
class PersonaRepositoryTest {

    @Autowired
    PersonaRepository _personaRepository;

    Persona pr1 = new Persona(1,"nicolas", "videla", 20);
    Persona pr2 = new Persona(2,"juan", "videla", 21);
    Persona pr3 = new Persona(3,"mariano", "videla", 22);

    @BeforeEach
    void filldb(){
        _personaRepository.saveAll(Arrays.asList(pr1, pr2, pr3));
    }

    @AfterEach
    void delete(){
        _personaRepository.deleteAll();
    }

    @Test
    void findByWordsPositivo() {
        String apellido = pr1.getApellido();

        List<Persona> result = _personaRepository.findByAny(apellido);

        assertEquals(3, result.size());
    }

    @Test
    void findByWordsNegativo() {
        String nombre = "susana";

        List<Persona> result = _personaRepository.findByAny(nombre);

        assertThat(result).isEmpty();
    }

    @Test
    void findByEdadPositivo() {
        String edad = ((Integer) pr2.getEdad()).toString();

        List<Persona> result = _personaRepository.findByAny(edad);

//        assertEquals(resul);
    }

    @Test
    void findByEdadNegativo() {
    }
}