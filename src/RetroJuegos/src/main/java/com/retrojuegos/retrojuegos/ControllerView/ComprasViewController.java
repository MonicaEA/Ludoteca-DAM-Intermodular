package com.retrojuegos.retrojuegos.ControllerView;
import com.retrojuegos.retrojuegos.Service.ComprasService;
import com.retrojuegos.retrojuegos.Service.NavegacionMenuService;
import com.retrojuegos.retrojuegos.Service.UsuarioActualService;
import com.retrojuegos.retrojuegos.dao.*;
import com.retrojuegos.retrojuegos.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private PlataformasDAO plataformasDAO = new PlataformasDAO();
    private GenerosDAO generosDAO = new GenerosDAO();
    private ComprasService compraService = new ComprasService();

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

        // menu principal, uso metodo externo para no repetirlo 4 veces
        btnVolver.setOnAction(event -> {
            NavegacionMenuService.irAMenuPrincipal(btnVolver);
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

            Usuarios usuario = UsuarioActualService.identificar();

            Clientes cliente = new Clientes(0, txtDni.getText(), txtNombre.getText(),
                    txtApellidos.getText(), txtEmail.getText(),
                    txtTelefono.getText(), TipoCliente.AMBOS);

            Videojuegos videojuego = new Videojuegos(0, txtTitulo.getText(),
                    Double.parseDouble(txtPrecioCompra.getText()),
                    Double.parseDouble(txtPrecioVenta.getText()),
                    comboPlataforma.getValue().getIdPlataforma(),
                    comboGenero.getValue().getIdGenero(),
                    choiceEstado.getValue(),
                    choiceTipo.getValue(),
                    usuario.getIdUsuario());

            compraService.procesarNuevaCompra(cliente, videojuego, usuario);

            mostrarAlerta("ÉXITO", "Compra guardada correctamente");
            limpiarTodo();

        } catch (SQLException e) {
            mostrarAlerta("ERROR BBDD", "No se pudo guardar: " + e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta("ERROR DATOS", "Revisa los precios introducidos");
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