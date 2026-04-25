package com.retrojuegos.retrojuegos.dao;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Videojuegos;


import java.sql.*;

public class VideojuegoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public int insertarJuego(Videojuegos juego) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "INSERT INTO videojuegos (titulo, precio_compra,precio_venta_estimado, id_plataforma, id_genero, estado, tipo_stock, id_usuario_registro) VALUES (?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, juego.getTitulo());
        preparedStatement.setDouble(2,juego.getPrecioCompra());
        preparedStatement.setDouble(3,juego.getPrecioVentaEstimada());
        preparedStatement.setInt(4,juego.getIdPlataforma());
        preparedStatement.setInt(5,juego.getIdGenero());
        preparedStatement.setString(6,juego.getEstado().name());
        preparedStatement.setString(7,juego.getTipo().name());
        preparedStatement.setInt(8,juego.getUsuarioRegistro());

        preparedStatement.executeUpdate();


        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1); // Devolvemos el ID del juego recién creado
        }
        return 0;


    }

    public java.util.List<Videojuegos> obtenerDisponibles() throws SQLException {
        java.util.List<Videojuegos> lista = new java.util.ArrayList<>();
        connection = DBConnection.getConnection();

        // Consulta limpia, solo leyendo la tabla videojuegos
        String query = "SELECT vj.* FROM videojuegos vj LEFT JOIN ventas v ON vj.id_juego = v.id_juego WHERE v.id_juego IS NULL";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Videojuegos juego = new Videojuegos(); // Constructor vacío

            juego.setIdJuego(resultSet.getInt("id_juego"));
            juego.setTitulo(resultSet.getString("titulo"));
            juego.setPrecioCompra(resultSet.getDouble("precio_compra"));
            juego.setPrecioVentaEstimada(resultSet.getDouble("precio_venta_estimado"));
            juego.setIdPlataforma(resultSet.getInt("id_plataforma"));
            juego.setIdGenero(resultSet.getInt("id_genero"));
            juego.setEstado(com.retrojuegos.retrojuegos.model.EstadoJuego.valueOf(resultSet.getString("estado").toUpperCase().replace(" ", "_")));
            juego.setTipo(com.retrojuegos.retrojuegos.model.TipoStock.valueOf(resultSet.getString("tipo_stock").toUpperCase().replace(" ", "_")));
            juego.setUsuarioRegistro(resultSet.getInt("id_usuario_registro"));

            lista.add(juego);
        }
        return lista;
    }

}
