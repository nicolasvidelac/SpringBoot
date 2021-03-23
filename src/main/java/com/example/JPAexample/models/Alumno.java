package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name= "Alumno")
public class Alumno extends Persona{

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String legajo;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn( name = "carrera_id", nullable = false )
    private Carrera carrera;

}