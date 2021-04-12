package com.example.JPAexample.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversidadDTO {
    private String nombre;
    private String calle;
    private int numeracion;
}
