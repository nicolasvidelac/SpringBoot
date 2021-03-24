package com.example.JPAexample.models.DTO;

import lombok.Getter;

@Getter
public class AlumnoDTO {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String legajo;
    private int carrera_id;
}
