package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Perfil;
import com.retrojuegos.retrojuegos.model.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Usuarios validarLogin(String dni, String pass) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM usuarios WHERE dni = ? AND pass = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,dni);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Usuarios(
                    resultSet.getInt("id_usuario"),
            resultSet.getString("nombre"),
            resultSet.getString("apellidos"),
            resultSet.getString("dni"),
            resultSet.getString("email"),
            resultSet.getString("pass"),
            Perfil.valueOf(resultSet.getString("perfil").toUpperCase())
            );
        }
        return null;

        }


    }




