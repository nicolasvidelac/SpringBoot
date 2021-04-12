package com.example.JPAexample.controllers;

import com.example.JPAexample.dtoServices.interfaces.CarreraDTOService;
import com.example.JPAexample.models.DTO.CarreraDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        CarreraDTO cr1 = new CarreraDTO(1, "nombre", "codigo", null);

        when(_CarreraDTOService.saveCarrera(cr1)).thenReturn(cr1);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.saveCarrera(cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(responseEntity.getBody(), cr1);

    }

    @Test
    void saveCarreraNegativoWithBlankNombre() {
        CarreraDTO cr1 = new CarreraDTO(1, "", "dddd", null);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.saveCarrera(cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).saveCarrera(cr1);

    }


    @Test
    void saveCarreraNegativoWithWhiteSpaceCodigo() {
        CarreraDTO cr1 = new CarreraDTO(1, "ddd", "    ", null);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.saveCarrera(cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).saveCarrera(cr1);

    }

    @Test
    void updateCarreraPositivo() {
        CarreraDTO cr1 = new CarreraDTO(1, "nombre", "codigo", null);

        when(_CarreraDTOService.updateCarrera(cr1.getId(), cr1)).thenReturn(cr1);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.updateCarrera(cr1.getId(), cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(responseEntity.getBody(), cr1);

    }

    @Test
    void updateCarreraNegativoWithBlankCodigo() {
        CarreraDTO cr1 = new CarreraDTO(1, "ddddd", "", null);

        ResponseEntity<CarreraDTO> responseEntity = _carreraController.updateCarrera(cr1.getId(), cr1);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
        verify(_CarreraDTOService, never()).updateCarrera(cr1.getId(), cr1);

    }


    @Test
    void updateCarreraNegativoWithWhiteSpaceNombre() {
        CarreraDTO cr1 = new CarreraDTO(1, "     ", "dddd", null);

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