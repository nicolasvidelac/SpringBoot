package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("SELECT a FROM Alumno a WHERE lower(a.legajo) like :termino or lower(a.nombre) like :termino or " +
            "lower(a.apellido) like :termino or lower(a.email) like :termino or cast(a.edad as text) like :termino")
    List<Alumno> findByWords(@Param("termino") String termino);
}
