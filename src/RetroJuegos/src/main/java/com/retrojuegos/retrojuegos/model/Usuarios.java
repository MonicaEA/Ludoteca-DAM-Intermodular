package com.retrojuegos.retrojuegos.model;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuarios {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String pass;
    private Perfil perfil;


}
