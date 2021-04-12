package com.example.JPAexample.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Carrera {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "universidad_id")
    private Universidad universidad;

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else return
                this.codigo.equals(((Carrera) object).codigo) &&
                        this.nombre.equals(((Carrera) object).nombre) &&
                        this.universidad.equals(((Carrera) object).universidad);
    }
}