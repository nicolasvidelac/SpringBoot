package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Carrera;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@SpringBootTest
class CarreraRepositoryTest {

    @Autowired
    private CarreraRepository _carreraRepository;


//    private Carrera cr2 = new Carrera(3,"abogacia", "car002");
//    private Carrera cr3 = new Carrera(4,"medicina", "car003");
//

    @Test
    @DataSet(value = "carreras.yml")
    void findByNombrePositivo() {
        Carrera cr1 = new Carrera(1,"ingenieria", "car001");
        String nombre = "ingenieria";
        List<Carrera> result = _carreraRepository.findAll();

        assertThat(result).isEqualTo(cr1);
    }

    @Test
    void findByNombreOrCodigoNegativo() {
        String nombre = "dsds";
        List<Carrera> result = _carreraRepository.findByAny(nombre);

        assertThat(result).isEmpty();
    }
}