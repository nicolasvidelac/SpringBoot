package com.example.JPAexample.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO implements Comparable<AlumnoDTO> {

    private Integer id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String legajo;
    private int carrera_id;
    private String carrera_nombre;
    private String carrera_codigo;

    @Override
    public int compareTo(AlumnoDTO o) {
        return this.carrera_id > o.carrera_id ? 1 : this.carrera_id < o.carrera_id ? -1 : 0;
    }
}
