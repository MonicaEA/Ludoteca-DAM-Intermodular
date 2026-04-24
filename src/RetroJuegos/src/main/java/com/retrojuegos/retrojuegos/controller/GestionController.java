package com.retrojuegos.retrojuegos.controller;

import com.retrojuegos.retrojuegos.data.DataSet;
import com.retrojuegos.retrojuegos.model.*;
import lombok.*;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;



public class GestionController {

    private static Usuarios usuarioLogueado;
    private static final String rutaVideojuegos = "src/main/java/resources/videojuegos.csv";
    private static final String rutaClientes = "src/main/java/resources/clientes.csv";
    private static Scanner sc = new Scanner(System.in);


// TODO
    // Metodos de logica para crear juegos, etc

    // metodo para crearCliente



    public static void setUsuarioLogueado(Usuarios u) {
        usuarioLogueado = u;
    }


}
