package com.retrojuegos.retrojuegos.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;


public class NavegacionMenuService {



    public static void irAMenuPrincipal(Control componenteOrigen) {
        try {
            Parent root = FXMLLoader.load(NavegacionMenuService.class.getResource("/com/retrojuegos/retrojuegos/main-view.fxml"));
            Stage stage = (Stage) componenteOrigen.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("MENÚ PRINCIPAL");
        } catch (Exception e) {
            System.err.println("Error en navegación: " + e.getMessage());
        }
    }


}
