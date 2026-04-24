package com.retrojuegos.retrojuegos.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Generos {
    private int idGenero;
    private String nombre;

    @Override
    public String toString() {
        return this.nombre;
    }
}
