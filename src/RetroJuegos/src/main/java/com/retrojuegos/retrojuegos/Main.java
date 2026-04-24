package com.retrojuegos.retrojuegos;

import com.retrojuegos.retrojuegos.database.DBConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Connection testDB = DBConnection.getConnection();
    }
}