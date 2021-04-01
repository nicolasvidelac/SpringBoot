package com.example.JPAexample.services;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.repositories.interfaces.CarreraRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

@DataJpaTest
class CarreraServiceImpTest {

    @Mock
    CarreraRepository _carreraRepository;
    AutoCloseable autoCloseable;
    CarreraServiceImp _carreraService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        _carreraService = new CarreraServiceImp(_carreraRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllCarreras() {
        _carreraService.getAllCarreras();
        verify(_carreraRepository).findAll();
    }

    @Test
    void saveCarreraPositivo() {
        Carrera cr1 = new Carrera(1, "nombre", "codigo");
        _carreraService.saveCarrera(cr1);

        ArgumentCaptor<Carrera> carreraArgumentCaptor = ArgumentCaptor.forClass(Carrera.class);
        verify(_carreraRepository).saveAndFlush(carreraArgumentCaptor.capture());
        Carrera capturedCarrera = carreraArgumentCaptor.getValue();

        assertThat(capturedCarrera).isEqualTo(cr1);
    }

    @Test
    void saveCarreraInvalida() {
        Carrera cr1 = new Carrera();

        given(_carreraRepository.saveAndFlush(cr1)).willThrow(DataIntegrityViolationException.class);

        assertThatThrownBy(() -> _carreraService.saveCarrera(cr1)).hasMessage("Los parámetros ingresados " +
                "no son válidos");
    }

    @Test
    void saveCarreraNegativo() {
        Carrera cr1 = new Carrera();

        given(_carreraRepository.saveAndFlush(cr1)).willThrow(EmptyResultDataAccessException.class);

        assertThatThrownBy(() -> _carreraService.saveCarrera(cr1)).hasMessage("No se pudo completar la solicitud");
    }

    @Test
    void deleteCarreraPositivo() {
        int numero = 1;

        assertThat(_carreraService.deleteCarrera(numero)).isTrue();
    }

    @Test
    void deleteCarreraInexistente() {
        int numero = 1;

        willThrow(EmptyResultDataAccessException.class).given(_carreraRepository).deleteById(numero);

        assertThatThrownBy(() -> _carreraService.deleteCarrera(numero)).hasMessage("Carrera con id '" +
                numero + "' no existe");
    }

    @Test
    void deleteCarreraSiendoUsado() {
        int numero = 1;

        willThrow(DataIntegrityViolationException.class).given(_carreraRepository).deleteById(numero);

        assertThatThrownBy(() -> _carreraService.deleteCarrera(numero)).hasMessage("La Carrera no puede ser" +
                " eliminada porque esta siendo utilizada por al menos un Alumno");

    }
}