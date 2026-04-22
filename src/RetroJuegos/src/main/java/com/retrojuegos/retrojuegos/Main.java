package com.retrojuegos.retrojuegos;

import com.retrojuegos.retrojuegos.database.DBConnection;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection testDB = DBConnection.getConnection();

    }

}
