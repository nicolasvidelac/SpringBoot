package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoServices.interfaces.CarreraDTOService;
import com.example.JPAexample.models.DTO.CarreraDTO;
import com.example.JPAexample.others.exceptions.RecordNotFoundException;
import com.example.JPAexample.repositories.interfaces.CarreraRepository;
import com.example.JPAexample.services.CarreraServiceImp;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
class CarreraControllerTest {

    @Mock
    CarreraDTOService _CarreraDTOService;
    @InjectMocks
    CarreraController _carreraController;


    @Test
    void getSingleCarreraPositivo() {
        int numero = 1;
        CarreraDTO cr1 = new CarreraDTO();

        when(_CarreraDTOService.getCarreraById(numero)).thenReturn(cr1);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.getSingleCarrera(numero);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(cr1, responseEntity.getBody());
    }

    @Test
    void getSingleCarreraNegativo() {
        int numero = -1;

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.getSingleCarrera(numero);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).getCarreraById(numero);
    }

    @Test
    void saveCarreraPositivo() {
        CarreraDTO cr1 = new CarreraDTO(1 , "nombre", "codigo");

        when(_CarreraDTOService.saveCarrera(cr1)).thenReturn(cr1);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.saveCarrera(cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(responseEntity.getBody(), cr1);

    }

    @Test
    void saveCarreraNegativoWithBlankNombre() {
        CarreraDTO cr1 = new CarreraDTO(1 , "", "dddd");

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.saveCarrera(cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).saveCarrera(cr1);

    }


    @Test
    void saveCarreraNegativoWithWhiteSpaceCodigo() {
        CarreraDTO cr1 = new CarreraDTO(1 , "ddd", "    ");

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.saveCarrera(cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).saveCarrera(cr1);

    }

    @Test
    void updateCarreraPositivo() {
        CarreraDTO cr1 = new CarreraDTO(1 , "nombre", "codigo");

        when(_CarreraDTOService.updateCarrera(cr1.getId(), cr1)).thenReturn(cr1);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.updateCarrera(cr1.getId(), cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(responseEntity.getBody(), cr1);

    }

    @Test
    void updateCarreraNegativoWithBlankCodigo() {
        CarreraDTO cr1 = new CarreraDTO(1 , "ddddd", "");

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.updateCarrera(cr1.getId(), cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).updateCarrera(cr1.getId(), cr1);

    }


    @Test
    void updateCarreraNegativoWithWhiteSpaceNombre() {
        CarreraDTO cr1 = new CarreraDTO(1 , "     ", "dddd");

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.updateCarrera(cr1.getId(), cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).updateCarrera(cr1.getId(), cr1);
    }


    @Test
    void deleteCarreraPositivo() {
        int numero = 1;

        when(_CarreraDTOService.deleteCarrera(numero)).thenReturn(true);
        ResponseEntity<Boolean> responseEntity = _carreraController.deleteCarrera(numero);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(responseEntity.getBody(), true);

    }

    @Test
    void deleteCarreraNegativo() {
        int numero = -1;

        ResponseEntity<Boolean> responseEntity = _carreraController.deleteCarrera(numero);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).deleteCarrera(numero);

    }


}