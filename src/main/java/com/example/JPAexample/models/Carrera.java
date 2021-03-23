package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Carrera")
public class Carrera implements Comparable<Carrera> {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String codigo;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumno;

    @Override
    public int compareTo(Carrera o) {
        return this.id > o.id ? 1 : this.id < o.id ? -1 : 0;
    }
}