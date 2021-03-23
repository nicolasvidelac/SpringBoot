package com.example.JPAexample.models;

import javax.persistence.*;

@Entity(name= "Alumno")
public class Alumno extends Persona implements Comparable<Alumno>{

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String legajo;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn( name = "carrera_id", nullable = false )
    private Carrera carrera;

    public Alumno(String nombre, String apellido, int edad, String legajo, String email, Carrera carrera) {
        super(nombre, apellido, edad);
        this.legajo = legajo;
        this.email = email;
        this.carrera = carrera;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Alumno() {
    }

    public Long getId() {
        return id;
    }

    public String getLegajo() {
        return legajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", legajo='" + legajo + '\'' +
                ", email='" + email + '\'' +
                ", carrera=" + carrera +
                '}';
    }

    @Override
    public int compareTo(Alumno o) {
        return this.id > o.id ? 1 : this.id < o.id ? -1 : 0;
    }
}
