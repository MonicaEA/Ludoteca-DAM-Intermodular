package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.dao.UsuarioDAO;
import com.retrojuegos.retrojuegos.model.Usuarios;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private TextField dniLogin;

    @FXML
    private PasswordField passLogin;

    @FXML
    private Button btnLogin;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnAction(event -> {
            validarAcesso();
        });
    }

    private void validarAcesso() {


        String dni = dniLogin.getText();
        String pass = passLogin.getText();

        if (dni.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Faltan datos", "Rellena los campos");
            return;
        }
        try {
            Usuarios user = usuarioDAO.validarLogin(dni, pass);
            if (user != null) {
                System.out.println("Datos correctos ,bienvenida al curro " + user.getNombre());
                UsuarioActualController.setUsuarioLogueado(user);
                //TODO: Aquí cargaré la ventana del menú
                cambiarAMenuPrincipal();

            } else {
                mostrarAlerta("Te has equivocado", "Error de datos de acceso!!!!");
            }
        } catch (SQLException e) {
            mostrarAlerta("Error", "No nos hemos conectado a la BBDD :(");
        }
    }

    private void cambiarAMenuPrincipal() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/main-view.fxml"));
            Parent root = loader.load();

            //crear ventana
            Stage stage = new Stage();
            stage.setTitle("RetroJuegos - MENÚ PRINCIPAL");
            stage.setScene(new Scene(root));
            stage.show();
            //cerrar la del login
            Stage actualStage = (Stage) btnLogin.getScene().getWindow();
            actualStage.close();

        }catch (IOException e){
            mostrarAlerta("Error","No se puede abrir el menú");
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
