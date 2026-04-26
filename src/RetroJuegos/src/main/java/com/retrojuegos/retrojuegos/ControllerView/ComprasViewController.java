package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.dao.*;
import com.retrojuegos.retrojuegos.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComprasViewController implements Initializable {

    @FXML
    private Button btnFinalizar, btnVolver;
    @FXML private
    ChoiceBox<EstadoJuego> choiceEstado;
    @FXML private
    ChoiceBox<TipoStock> choiceTipo;
    @FXML private
    ComboBox<Generos> comboGenero;
    @FXML private
    ComboBox<Plataformas> comboPlataforma;
    @FXML private
    TextField txtApellidos, txtDni, txtEmail, txtNombre, txtPrecioCompra, txtTelefono, txtTitulo, txtPrecioVenta;

    private ClientesDAO clientesDAO = new ClientesDAO();
    private VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    private ComprasDAO comprasDAO = new ComprasDAO();
    private PlataformasDAO plataformasDAO = new PlataformasDAO();
    private GenerosDAO generosDAO = new GenerosDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        actions();
    }

    private void actions() {
        configurarCombosYChoices();
        configurarListeners();
        configurarBotones();
    }

    // desplegables del menu

    private void configurarCombosYChoices() {

        choiceEstado.getItems().setAll(EstadoJuego.values());
        choiceTipo.getItems().setAll(TipoStock.values());

        try {
            comboPlataforma.getItems().setAll(plataformasDAO.todasPlataformas());
            comboPlataforma.getSelectionModel().selectFirst();

            comboGenero.getItems().setAll(generosDAO.todasGeneros());
            comboGenero.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            System.out.println("Error al cargar combos: " + e.getMessage());
        }
    }

    private void configurarListeners() {
        // Listener autocompleta con el teelefono
        txtTelefono.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                buscarCliente();
            }
        });
    }

    private void configurarBotones() {
        // finalizar
        btnFinalizar.setOnAction(event -> ejecutarCompra());

        // menu principal
        btnVolver.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/main-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnVolver.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("MENÚ PRINCIPAL");
            } catch (IOException e) {
                System.out.println("Error al volver al menú principal");
            }
        });
    }

    private void buscarCliente() {
        String movil = txtTelefono.getText();
        if (movil.isEmpty()) return;

        try {
            Clientes cliente = clientesDAO.buscarPorTelefono(movil);
            if (cliente != null) {
                txtDni.setText(cliente.getDni());
                txtNombre.setText(cliente.getNombre());
                txtApellidos.setText(cliente.getApellidos());
                txtEmail.setText(cliente.getEmail());
                permitirEdicion(false);
            } else {
                limpiarCamposCliente();
                permitirEdicion(true);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    private void ejecutarCompra() {
        try {
            Usuarios usuario = UsuarioActualController.getUsuarioLogueado();
            Clientes cliente = clientesDAO.buscarPorTelefono(txtTelefono.getText());

            if (cliente == null) {
                cliente = new Clientes(0, txtDni.getText(), txtNombre.getText(), txtApellidos.getText(), txtEmail.getText(), txtTelefono.getText(), TipoCliente.AMBOS);
                clientesDAO.insertarCliente(cliente);
                cliente = clientesDAO.buscarPorTelefono(txtTelefono.getText());
            }

            Videojuegos videojuego = new Videojuegos(
                    0,
                    txtTitulo.getText(),
                    Double.parseDouble(txtPrecioCompra.getText()),
                    Double.parseDouble(txtPrecioVenta.getText()),
                    comboPlataforma.getValue().getIdPlataforma(),
                    comboGenero.getValue().getIdGenero(),
                    choiceEstado.getValue(),
                    choiceTipo.getValue(),
                    usuario.getIdUsuario()
            );

            int idJuegoGenerado = videojuegoDAO.insertarJuego(videojuego);

            Compras compra = new Compras(0, java.time.LocalDate.now(), idJuegoGenerado, usuario.getIdUsuario(), cliente.getIdCliente());
            comprasDAO.insertarCompra(compra);

            mostrarAlerta("ÉXITO", "Compra guardada correctamente");
            limpiarTodo();

        } catch (SQLException e) {
            mostrarAlerta("ERROR BBDD", e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta("ERROR DATOS", "Los precios deben ser números");
        }
    }


    private void permitirEdicion(boolean editable) {
        txtDni.setEditable(editable);
        txtNombre.setEditable(editable);
        txtApellidos.setEditable(editable);
        txtEmail.setEditable(editable);
    }

    private void limpiarCamposCliente() {
        txtDni.clear();
        txtNombre.clear();
        txtApellidos.clear();
        txtEmail.clear();
    }

    private void limpiarTodo() {
        txtTitulo.clear();
        txtPrecioCompra.clear();
        txtPrecioVenta.clear();
        txtTelefono.clear();
        limpiarCamposCliente();
        permitirEdicion(true);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}