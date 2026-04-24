package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Plataformas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlataformasDAO {

    public List<Plataformas> todasPlataformas() throws SQLException {
        List<Plataformas> lista = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM plataformas");

        while(resultSet.next()){
            lista.add(new Plataformas(resultSet.getInt("id_plataforma"),resultSet.getString("nombre_plataforma")));
        }
        resultSet.close();
        statement.close();
        return lista;
    }
}
