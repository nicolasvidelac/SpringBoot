package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT c FROM Carrera c WHERE lower(c.nombre) = :termino OR lower(c.codigo) = :termino")
    List<Carrera> findByAny(@Param("termino") String termino);
}
