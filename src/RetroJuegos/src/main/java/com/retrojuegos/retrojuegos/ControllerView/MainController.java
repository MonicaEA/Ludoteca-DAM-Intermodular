package com.retrojuegos.retrojuegos.ControllerView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnComprar, btnVender, btnStock, btnFinanzas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnComprar.setOnAction(event->{
            System.out.println("PAsando a pantalla de compras...");

            //todo cambio de pantallas
        });

        btnVender.setOnAction(event->{
            System.out.println("Pantalla de ventas...");
        });

        btnStock.setOnAction(event->{
            System.out.println("Pantalla de stock");
        });

        btnFinanzas.setOnAction(event->{
            System.out.println("Pantalla de los euritos...");
        });



    }
}
