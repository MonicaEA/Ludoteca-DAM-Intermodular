package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Clientes;
import com.retrojuegos.retrojuegos.model.TipoCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    // creo un metodo para buscar por el movil
    public Clientes buscarPorTeléfono(String telefono) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM clientes WHERE telefono = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,telefono);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new Clientes(
                    resultSet.getInt("id_cliente"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellidos"),
                    resultSet.getString("dni"),
                    resultSet.getString("email"),
                    resultSet.getString("telefono"),
                    TipoCliente.valueOf(resultSet.getString("tipo_cliente"))
            );
        }
        return null;


    }

}
