package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query("SELECT p from Persona p where lower(p.nombre) like :termino or lower(p.apellido) like :termino or " +
            "cast(p.edad as text) like :termino")
    List<Persona> findByAny(@Param("termino") String termino);
}
