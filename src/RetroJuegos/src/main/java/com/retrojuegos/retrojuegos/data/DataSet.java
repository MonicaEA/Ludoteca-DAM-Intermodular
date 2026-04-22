package com.retrojuegos.retrojuegos.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.retrojuegos.retrojuegos.model.*;

public class DataSet {

    // 1. Lista de Usuarios (Para probar el Login)
    private static ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList(
            new Usuarios(1, "Beatriz", "Retro", "12345678A", "bea@retrojuegos.com", "1234", Perfil.ADMINISTRADOR),
            new Usuarios(2, "Monica", "Gamer", "87654321B", "monica@retrojuegos.com", "4321", Perfil.TRABAJADOR)
    );

    // 2. Lista de Clientes (Para la agenda)
    private static ObservableList<Clientes> listaClientes = FXCollections.observableArrayList(
            new Clientes(1, "Pepe", "Perez", "11122233C", "pepe@gmail.com", "600111222", TipoCliente.AMBOS),
            new Clientes(2, "Ana", "Lopez", "44455566D", "ana@hotmail.com", "655444333", TipoCliente.COMPRADOR)
    );

    // 3. Lista de Videojuegos (Para el stock)
    private static ObservableList<Videojuegos> listaJuegos = FXCollections.observableArrayList(
            // Orden: id, titulo, pCompra, pVenta, idPlat, idGen, ESTADO, TIPO, idUsu
            new Videojuegos(1, "Super Mario Bros", 15.0, 40.0, 1, 1, EstadoJuego.CAJA_SIN_MANUAL, TipoStock.PROPIO, 1),
            new Videojuegos(2, "The Legend of Zelda", 50.0, 120.0, 1, 1, EstadoJuego.COMPLETO, TipoStock.COMUN, 1)
    );

    // Métodos para que los controladores "pidan" los datos
    public static ObservableList<Usuarios> getUsuarios() { return listaUsuarios; }
    public static ObservableList<Clientes> getClientes() { return listaClientes; }
    public static ObservableList<Videojuegos> getJuegos() { return listaJuegos; }
}