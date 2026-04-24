package com.retrojuegos.retrojuegos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionStart extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AplicacionStart.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600 ,400);
        stage.setTitle("Bienvenido a ReTrOJuEgOs");
        stage.setScene(scene);
        stage.show();
    }
}

