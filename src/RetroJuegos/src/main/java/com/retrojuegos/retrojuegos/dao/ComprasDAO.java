package com.retrojuegos.retrojuegos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ComprasDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertarCompra(com.retrojuegos.retrojuegos.model.Compras c) throws java.sql.SQLException {
        connection = com.retrojuegos.retrojuegos.database.DBConnection.getConnection();
        String query = "INSERT INTO compras (id_juego, id_cliente, id_usuario, fecha_compra) VALUES (?,?,?,?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, c.getIdJuego());
        preparedStatement.setInt(2, c.getIdCliente());
        preparedStatement.setInt(3, c.getIdUsuario());
        preparedStatement.setDate(4, java.sql.Date.valueOf(c.getFechaCompra()));

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
