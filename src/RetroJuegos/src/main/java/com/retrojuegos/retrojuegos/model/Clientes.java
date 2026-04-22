package com.retrojuegos.retrojuegos.model;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Clientes {
     private int idCliente;
     private String nombre;
     private String apellidos;
     private String dni;
     private String email;
     private String telefono;
     private TipoCliente tipoCliente;





}
