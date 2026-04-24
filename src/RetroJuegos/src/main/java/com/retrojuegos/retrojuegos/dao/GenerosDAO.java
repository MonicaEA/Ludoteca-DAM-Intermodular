package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Generos;
import com.retrojuegos.retrojuegos.model.Plataformas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenerosDAO {

    public List<Generos> todasGeneros() throws SQLException {
        List<Generos> lista = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM generos");

        while(resultSet.next()){
            lista.add(new Generos(resultSet.getInt("id_genero"),resultSet.getString("nombre_genero")));
        }
        resultSet.close();
        statement.close();
        return lista;
    }
}
