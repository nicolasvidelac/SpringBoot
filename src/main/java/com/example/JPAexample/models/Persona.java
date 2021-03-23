package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Persona")
@Inheritance( strategy = InheritanceType.JOINED)
public class Persona implements Comparable<Persona>{

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Long id;

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