package com.example.JPAexample.models;

import javax.persistence.*;

@Entity(name = "Persona")
@Inheritance
public class Persona {

    @Id @GeneratedValue @Column(name = "id", updatable = false)
    private Long id;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private String apellido;

    @Column( nullable = false)
    private int edad;

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Persona(){

    }

    public Long getId() {
        return id;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


}
