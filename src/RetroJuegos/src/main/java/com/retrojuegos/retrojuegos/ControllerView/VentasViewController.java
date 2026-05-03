package com.retrojuegos.retrojuegos.ControllerView;
import com.retrojuegos.retrojuegos.Service.NavegacionMenuService;
import com.retrojuegos.retrojuegos.Service.UsuarioActualService;
import com.retrojuegos.retrojuegos.Service.VentasService;
import com.retrojuegos.retrojuegos.dao.VideojuegoDAO;
import com.retrojuegos.retrojuegos.model.Usuarios;
import com.retrojuegos.retrojuegos.model.Videojuegos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VentasViewController implements Initializable {

    @FXML private Button btnEliminar, btnFinalizar, btnVolver;
    @FXML private TableColumn<Videojuegos, Double> colCarritoPrecio, colTabPrecio;
    @FXML private TableColumn<Videojuegos, String> colCarritoTitulo, colTabPlataforma, colTabTitulo;
    @FXML private Label lblTotal;
    @FXML private TableView<Videojuegos> tablaCarrito, tablaCatalogo;
    @FXML private TextField txtBuscar;

    private ObservableList<Videojuegos> listaCatalogo = FXCollections.observableArrayList();
    private ObservableList<Videojuegos> listaCarrito = FXCollections.observableArrayList();


    private VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    private VentasService ventasService = new VentasService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actions();
    }

    private void actions() {
        configurarColumnas();
        cargarCatalogo();
        configurarBuscador();
        configurarCarrito();
        configurarBotones();
    }

    private void configurarBotones() {

        btnEliminar.setOnAction(event -> {
            Videojuegos juegoSeleccionado = tablaCarrito.getSelectionModel().getSelectedItem();
            if (juegoSeleccionado != null) {
                listaCarrito.remove(juegoSeleccionado);
                listaCatalogo.add(juegoSeleccionado);
                actualizarVisualizacionTotal();
            } else {
                mostrarAlerta("AVISO", "Selecciona un juego del carrito para eliminar");
            }
        });

        btnVolver.setOnAction(event -> NavegacionMenuService.irAMenuPrincipal(btnVolver));

        btnFinalizar.setOnAction(event -> {
            Usuarios usuarios = UsuarioActualService.identificar();
            boolean exito = ventasService.procesarVentaCompleta(listaCarrito, usuarios.getIdUsuario());

            if (exito) {
                mostrarAlerta("Venta realizada", "Registrada correctamente");
                listaCarrito.clear();
                actualizarVisualizacionTotal();
                cargarCatalogo();
            } else {
                mostrarAlerta("Error", "No se ha podido procesar la venta (¿Carrito vacío?)");
            }
        });
    }

    private void actualizarVisualizacionTotal() {
        double total = ventasService.calcularTotalCarrito(listaCarrito);
        lblTotal.setText(String.format("Total: %.2f €", total));
    }

    private void configurarColumnas() {
        colTabTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colTabPlataforma.setCellValueFactory(new PropertyValueFactory<>("idPlataforma"));
        colTabPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVentaEstimada"));
        colCarritoTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colCarritoPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVentaEstimada"));
    }

    private void cargarCatalogo() {
        try {
            listaCatalogo.setAll(videojuegoDAO.obtenerDisponibles());
        } catch (SQLException e) {
            System.err.println("Error al cargar catálogo: " + e.getMessage());
        }
    }

    private void configurarBuscador() {
        FilteredList<Videojuegos> listaFiltrada = new FilteredList<>(listaCatalogo, b -> true);
        txtBuscar.textProperty().addListener((obs, old, val) -> {
            listaFiltrada.setPredicate(juego -> {
                if (val == null || val.isEmpty()) return true;
                return juego.getTitulo().toLowerCase().contains(val.toLowerCase());
            });
        });
        SortedList<Videojuegos> listaOrdenada = new SortedList<>(listaFiltrada);
        listaOrdenada.comparatorProperty().bind(tablaCatalogo.comparatorProperty());
        tablaCatalogo.setItems(listaOrdenada);
    }

    private void configurarCarrito() {
        tablaCarrito.setItems(listaCarrito);
        tablaCatalogo.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tablaCatalogo.getSelectionModel().getSelectedItem() != null) {
                Videojuegos seleccionado = tablaCatalogo.getSelectionModel().getSelectedItem();
                listaCarrito.add(seleccionado);
                listaCatalogo.remove(seleccionado);
                actualizarVisualizacionTotal();
            }
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}