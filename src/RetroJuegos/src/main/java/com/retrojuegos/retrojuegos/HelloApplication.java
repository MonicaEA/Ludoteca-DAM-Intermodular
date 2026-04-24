package com.retrojuegos.retrojuegos;

import com.retrojuegos.retrojuegos.database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600 ,400);
        stage.setTitle("Bienvenido a ReTrOJuEgOs");
        stage.setScene(scene);
        stage.show();
    }
}

