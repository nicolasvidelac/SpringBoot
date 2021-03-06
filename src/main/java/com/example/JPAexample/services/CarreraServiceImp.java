package com.example.JPAexample.services;

import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.Universidad;
import com.example.JPAexample.others.exceptions.MissingInfoException;
import com.example.JPAexample.others.exceptions.NotAcceptableException;
import com.example.JPAexample.others.exceptions.RecordNotFoundException;
import com.example.JPAexample.repositories.interfaces.CarreraRepository;
import com.example.JPAexample.repositories.interfaces.UniversidadRepository;
import com.example.JPAexample.services.interfaces.CarreraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImp implements CarreraService {

    @Autowired
    private final CarreraRepository _carreraRepository;
    @Autowired
    private final UniversidadRepository _universidadRepository;
    @Autowired
    private ModelMapper _modelMapper;
    @Autowired
    @Qualifier("getUTN")
    private Universidad _getUTN;
    @Autowired
    @Qualifier("getUBA")
    private Universidad _getUBA;

    public CarreraServiceImp(CarreraRepository _carreraRepository, UniversidadRepository _universidadRepository) {
        this._universidadRepository = _universidadRepository;
        this._carreraRepository = _carreraRepository;

    }

    public Carrera saveCarrera(Carrera newCarrera) {
        try {
            newCarrera.setUniversidad(_getUTN);
            newCarrera = _carreraRepository.saveAndFlush(newCarrera);
        } catch (DataIntegrityViolationException e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        } catch (Exception e) {
            throw new NotAcceptableException("No se pudo completar la solicitud");
        }
        return newCarrera;
    }

    public Carrera updateCarrera(int id, Carrera updateCarrera) {

        Carrera entity = _carreraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Carrera con id '" + id + "' no existe"
        ));

        entity.setNombre(updateCarrera.getNombre());
        entity.setCodigo(updateCarrera.getCodigo());

        try {
            entity = _carreraRepository.saveAndFlush(entity);
        } catch (Exception e) {
            throw new MissingInfoException("Los parámetros ingresados no son válidos");
        }

        return entity;
    }

    public Carrera getCarreraById(int id) {

        return _carreraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                "Carrera con id '" + id + "' no existe"
        ));
    }

    public List<Carrera> getAllCarreras() {

        List<Carrera> carreras = _carreraRepository.findAll();
        return carreras;
    }

    public boolean deleteCarrera(int id) {
        try {
            _carreraRepository.deleteById(id);
            return true;

        } catch (EmptyResultDataAccessException e) {
            throw new RecordNotFoundException(
                    "Carrera con id '" + id + "' no existe"
            );
        } catch (DataIntegrityViolationException e) {
            throw new NotAcceptableException(
                    "La Carrera no puede ser eliminada porque esta siendo utilizada por al menos un Alumno"
            );
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Carrera> getCarreraByAny(String termino) {
        return _carreraRepository.findByAny(termino.toLowerCase());
    }
}
