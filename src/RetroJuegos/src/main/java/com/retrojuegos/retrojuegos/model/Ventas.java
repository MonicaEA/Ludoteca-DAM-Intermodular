package com.retrojuegos.retrojuegos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ventas {

     private int idVenta;
     private int idJuego;
     private int idUsuario;
     private int idCliente;
     private String titulo;
     private double precioVenta;
     private double beneficioMonica;
     private double beneficioBetty;
     private LocalDate fechaVenta;

    public String toCSV() {
        return idVenta + ";" + titulo + ";" + precioVenta + ";" + beneficioMonica + ";" + beneficioBetty + ";" + fechaVenta;
    }

}
