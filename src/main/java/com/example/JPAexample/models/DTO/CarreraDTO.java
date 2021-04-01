package com.example.JPAexample.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarreraDTO implements Comparable<CarreraDTO> {
    private Integer id;
    private String nombre;
    private String codigo;

    @Override
    public int compareTo(CarreraDTO o) {
        return this.id > o.id ? 1 : this.id < o.id ? -1 : 0;
    }
}
