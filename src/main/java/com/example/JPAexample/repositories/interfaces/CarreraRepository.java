package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    List<Carrera> findByNombreOrCodigo(String nombre, String Codigo);
}
