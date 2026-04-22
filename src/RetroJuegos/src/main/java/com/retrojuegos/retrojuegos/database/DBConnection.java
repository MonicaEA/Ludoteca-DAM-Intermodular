package com.retrojuegos.retrojuegos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        String user = "root";
        String pass = "";
        String url = "127.0.0.1";
        String port = "3306";
        String dbName = "retrojuegos";
        String urlJDBC = String.format("jdbc:mysql://%s:%s/%s",url,port,dbName);

        try{
            connection = DriverManager.getConnection(urlJDBC,user,pass);
            System.out.println("Conexión a Retrojuegos establecida");
        } catch (SQLException e) {
            System.out.println("Error en la conexióx");
            System.out.println(e.getMessage());
        }

    }


}
