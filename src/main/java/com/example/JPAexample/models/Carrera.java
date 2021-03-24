package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Carrera")
@Getter
@Setter
public class Carrera {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String codigo;
}