package com.retrojuegos.retrojuegos.ControllerView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Button btnComprar, btnVender, btnStock, btnFinanzas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        actions();
    }

    private void actions() {
        configurarBotones();
    }

    private void configurarBotones() {
        btnComprar.setOnAction(event -> abrirVentana("/com/retrojuegos/retrojuegos/compra-view.fxml", "REGISTRO DE COMPRA", btnComprar));
        btnVender.setOnAction(event -> abrirVentana("/com/retrojuegos/retrojuegos/ventas-view.fxml", "REGISTRO DE VENTAS", btnVender));
        btnStock.setOnAction(event -> abrirVentana("/com/retrojuegos/retrojuegos/stock-view.fxml", "LISTADO STOCK", btnStock));
        btnFinanzas.setOnAction(event -> abrirVentana("/com/retrojuegos/retrojuegos/finanzas-view.fxml", "FINANZAS", btnFinanzas));

    }

    private void abrirVentana(String fxmlPath, String titulo, Button botonOrigen) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

            // Cerramos la ventana actual
            ((Stage) botonOrigen.getScene().getWindow()).close();

        } catch (IOException e) {
            System.err.println("Error al cargar la escena: " + fxmlPath);
            e.printStackTrace();
        }
    }

}
