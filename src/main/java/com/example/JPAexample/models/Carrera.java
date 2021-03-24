package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Carrera")
public class Carrera implements Comparable<Carrera> , Serializable {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Override
    public int compareTo(Carrera o) {
        return this.id > o.id ? 1 : this.id < o.id ? -1 : 0;
    }
}