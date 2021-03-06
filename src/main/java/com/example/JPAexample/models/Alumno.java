package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Alumno")
@Getter
@Setter
public class Alumno extends Persona {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String legajo;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;

    public Alumno(Integer id, String legajo, String email, Carrera carrera, String nombre, String apellido, int edad) {
        super(id, nombre, apellido, edad);
        this.id = id;
        this.legajo = legajo;
        this.email = email;
        this.carrera = carrera;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else return
                this.getNombre().equals(((Alumno) object).getNombre()) &&
                        this.getLegajo().equals(((Alumno) object).getLegajo()) &&
                        this.getApellido().equals(((Alumno) object).getApellido()) &&
                        this.getCarrera().equals(((Alumno) object).getCarrera()) &&
                        this.getEmail().equals(((Alumno) object).getEmail()) &&
                        this.getEdad() == ((Alumno) object).getEdad();
    }
}