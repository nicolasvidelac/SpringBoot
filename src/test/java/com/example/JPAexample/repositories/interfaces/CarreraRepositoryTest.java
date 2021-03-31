package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Carrera;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@DataJpaTest
class CarreraRepositoryTest {

    @Autowired
    private CarreraRepository _carreraRepository;

    Carrera cr1 = new Carrera(1, "ingenieria", "car001");
    Carrera cr2 = new Carrera(2,"abogacia", "car002");
    Carrera cr3 = new Carrera(3,"medicina", "car003");

    @BeforeEach
    void filldb(){
        _carreraRepository.saveAll(Arrays.asList(cr1,cr2,cr3));
    }

    @AfterEach
    void delete(){
        _carreraRepository.deleteAll();
    }

    @Test
    @DataSet(value = "carreras.yml", cleanBefore = true, cleanAfter = true)
    void findByNombrePositivo() {
        String nombre = "ingenieria";

        List<Carrera> result = _carreraRepository.findByAny(nombre);

        assertEquals(result.get(0), cr1);
    }

    @Test
    @DataSet(value = "carreras.yml", cleanBefore = true, cleanAfter = true)
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
}