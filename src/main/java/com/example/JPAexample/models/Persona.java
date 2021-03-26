package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Persona")
@Inheritance( strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Persona {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Integer id;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private String apellido;

    @Column( nullable = false)
    private int edad;

}