package com.retrojuegos.retrojuegos.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuegos {

    private Integer idJuego;
    private String titulo;
    private double precioCompra;
    private double precioVentaEstimada;
    private Integer idPlataforma;
    private int idGenero;
    private EstadoJuego estado;
    private TipoStock tipo;
    private int usuarioRegistro;





}
