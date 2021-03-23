package com.example.JPAexample.models;

import javax.persistence.*;
import java.util.List;

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

    public Carrera(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Carrera() {
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", alumno=" + alumno +
                '}';
    }

    @Override
    public int compareTo(Carrera o) {
        return this.id > o.id ? 1 : this.id < o.id ? -1 : 0;
    }
}
