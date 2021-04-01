package com.example.JPAexample.repositories;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.repositories.interfaces.CarreraRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@DataJpaTest
class CarreraRepositoryTest {

    Carrera cr1 = new Carrera(1, "ingenieria", "car001");
    Carrera cr2 = new Carrera(2, "abogacia", "car002");
    Carrera cr3 = new Carrera(3, "medicina", "car003");
    @Autowired
    private CarreraRepository _carreraRepository;

    @BeforeEach
    void filldb() {
        cr1 = _carreraRepository.save(cr1);
        cr2 = _carreraRepository.save(cr2);
        cr3 = _carreraRepository.save(cr3);
    }

    @Test
    void findByNombrePositivo() {
        String nombre = "ingenieria";
        List<Carrera> result = _carreraRepository.findByAny(nombre);
        assertEquals(result.get(0), cr1);
    }

    @Test
    void findByCodigoPositivo() {
        String codigo = "car002";
        List<Carrera> result = _carreraRepository.findByAny(codigo);
        assertEquals(result.get(0), cr2);
    }

    @Test
    void findByNombreNegativo() {
        String nombre = "dsds";
        List<Carrera> result = _carreraRepository.findByAny(nombre);
        assertThat(result).isEmpty();
    }

    @Test
    @DataSet(value = "carreras.yml", cleanBefore = true, cleanAfter = true)
    void findByCodigoNegativo() {
        String codigo = "car005";
        List<Carrera> result = _carreraRepository.findByAny(codigo);
        assertThat(result).isEmpty();
    }

    @Test
    void saveCarreraPositivo() {
        Carrera cr4 = new Carrera(4, "contabilidad", "car004");
        Carrera result = _carreraRepository.save(cr4);
        assertEquals(result, cr4);
    }

    @Test
    void deleteCarreraPositivo() {
        _carreraRepository.deleteById(cr1.getId());
        assertThat(_carreraRepository.findById(cr1.getId())).isEmpty();
    }


}