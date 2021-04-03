package com.example.JPAexample.repositories.interfaces;

import com.example.JPAexample.models.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Integer> {
}
