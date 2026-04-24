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


    //registrar cliente
    public void insertarCliente(Clientes clientes) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "INSERT INTO clientes(telefono,dni,nombre,apellidos,email,tipo_cliente) VALUES (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,clientes.getTelefono());
        preparedStatement.setString(2,clientes.getDni());
        preparedStatement.setString(3,clientes.getNombre());
        preparedStatement.setString(4,clientes.getApellidos());
        preparedStatement.setString(5,clientes.getEmail());
        preparedStatement.setString(6,clientes.getTipoCliente().name());

        preparedStatement.executeUpdate();
        preparedStatement.close();


    }


    // creo un metodo para buscar por el movil
    public Clientes buscarPorTelefono(String telefono) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM clientes WHERE telefono = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,telefono);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new Clientes(
                    resultSet.getInt("id_cliente"),
                    resultSet.getString("telefono"),
                    resultSet.getString("dni"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellidos"),
                    resultSet.getString("email"),
                    TipoCliente.valueOf(resultSet.getString("tipo_cliente").toUpperCase())
            );
        }
        return null;


    }

}
