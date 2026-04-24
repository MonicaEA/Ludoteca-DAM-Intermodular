package com.retrojuegos.retrojuegos.model;
import lombok.*;



@Getter
public class Clientes {
     private int idCliente;
     private String telefono;
     private String dni;
     private String nombre;
     private String apellidos;
     private String email;
     private TipoCliente tipoCliente;

     public String toCSV(){
          return idCliente+";"+nombre+";"+apellidos+";"+email+";"+telefono;
     }

     public Clientes(int idCliente, String telefono, String dni, String nombre, String apellidos, String email, TipoCliente tipoCliente) {
          this.idCliente = idCliente;
          this.telefono = telefono;
          this.dni = dni;
          this.nombre = nombre;
          this.apellidos = apellidos;
          this.email = email;
          this.tipoCliente = tipoCliente;
     }


}
