package com.retrojuegos.retrojuegos.dao;
import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Videojuegos;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class VentasDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean registrarVenta(List<Videojuegos> carrito, int idUsuario) {
        String query = "INSERT INTO ventas (id_juego, id_usuario, id_cliente, fecha_venta, precio_final) VALUES (?,?,?,?,?)";
        Connection connection = null;

        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(query);

            for (Videojuegos juego : carrito) {
                ps.setInt(1, juego.getIdJuego());
                ps.setInt(2, idUsuario);
                ps.setInt(3, 1); // como no quiero que registre en el programa quien lo ha comprado marco por defecto la posicion 1 en la BBDD
                ps.setDate(4, Date.valueOf(LocalDate.now()));
                ps.setDouble(5, juego.getPrecioVentaEstimada());
                ps.addBatch();
            }

            ps.executeBatch();
            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;

        } finally {

            try {
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public double obtenerSumaTotalVentas() throws SQLException {
        double total = 0.0;
        connection = DBConnection.getConnection();


        String query = "SELECT SUM(v.precio_venta_estimado) AS total " +
                "FROM ventas ve " +
                "JOIN videojuegos v ON ve.id_juego = v.id_juego";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            total = resultSet.getDouble("total");
        }

        preparedStatement.close();
        return total;
    }

    public double obtenerBeneficioNeto(String tipoStock) throws SQLException {
        double beneficio = 0.0;
        connection = DBConnection.getConnection();

        // Sumamos la diferencia entre venta estimada y compra para ese tipo de stock
        String query = "SELECT SUM(v.precio_venta_estimado - v.precio_compra) AS beneficio " +
                "FROM ventas ve " +
                "JOIN videojuegos v ON ve.id_juego = v.id_juego " +
                "WHERE v.tipo_stock = ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, tipoStock.toLowerCase());

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            beneficio = resultSet.getDouble("beneficio");
        }
        preparedStatement.close();
        return beneficio;
    }

    }




