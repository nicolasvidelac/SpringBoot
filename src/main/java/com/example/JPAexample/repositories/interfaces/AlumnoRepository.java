package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Alumno;
import com.example.JPAexample.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    List<Alumno> findByLegajoOrEmailOrNombreOrApellido(String legajo, String email, String nombre, String apellido);

    List<Alumno> findByEdadOrId(Integer edad, Integer id);

}
