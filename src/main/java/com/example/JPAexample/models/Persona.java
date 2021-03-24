package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Persona")
@Inheritance( strategy = InheritanceType.JOINED)
public class Persona implements Comparable<Persona>, Serializable {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Integer id;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private String apellido;

    @Column( nullable = false)
    private int edad;

    @Override
    public int compareTo(Persona o) {
        {
            return this.edad > o.edad ? 1 : this.edad < o.edad ? -1 : 0;
        }
    }
}