package com.retrojuegos.retrojuegos.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuegos {

    private int idJuego;
    private String titulo;
    private double precioCompra;
    private double precioVentaEstimada;
    private int idPlataforma;
    private int idGenero;
    private EstadoJuego estado;
    private TipoStock tipo;
    private int usuarioRegistro;



}
