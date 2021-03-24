package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name= "Alumno")
public class Alumno extends Persona implements Serializable {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String legajo;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn( name = "carrera_id", nullable = false )
    private Carrera carrera;

}