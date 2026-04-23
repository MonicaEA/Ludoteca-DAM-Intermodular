package com.retrojuegos.retrojuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compras {
    private int idCompra;
    private int idJuego;
    private double precioCompra;
    private LocalDate fechaCompra;
    private int idUsuario;


    public String toCSV() {
        return idCompra + ";" + idJuego + ";" + precioCompra + ";" + fechaCompra + ";" + idUsuario;
    }

}
