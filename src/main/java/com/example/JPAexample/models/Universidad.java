package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Universidad")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Universidad {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String calle;
    @Column
    private int numeracion;
}
