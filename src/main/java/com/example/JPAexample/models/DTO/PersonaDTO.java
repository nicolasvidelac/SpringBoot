package com.example.JPAexample.models.DTO;

import com.example.JPAexample.models.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO implements Comparable<PersonaDTO>{
    private Integer id;
    private String nombre;
    private String apellido;
    private int edad;

    @Override
    public int compareTo(PersonaDTO o) {
        return this.edad > o.edad ? 1 : this.edad < o.edad ? -1 : 0;
    }
}
