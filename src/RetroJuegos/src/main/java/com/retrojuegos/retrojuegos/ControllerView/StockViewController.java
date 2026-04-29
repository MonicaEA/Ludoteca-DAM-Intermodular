package com.retrojuegos.retrojuegos.ControllerView;
import com.retrojuegos.retrojuegos.dao.GenerosDAO;
import com.retrojuegos.retrojuegos.dao.PlataformasDAO;
import com.retrojuegos.retrojuegos.dao.VideojuegoDAO;
import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.*;
import com.retrojuegos.retrojuegos.util.ExportadorXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.ResourceBundle;

public class StockViewController implements Initializable {

    @FXML private Button btnLimpiar, btnMenu,exportarXML;
    @FXML private ComboBox<String> comboEstado, comboGenero, comboPlataforma, comboStock;
    @FXML private TableView<Videojuegos> tablaStock;
    @FXML private TextField txtBuscar;

    @FXML private TableColumn<Videojuegos, String> colEstado;
    @FXML private TableColumn<Videojuegos, Double> colPrecio;
    @FXML private TableColumn<Videojuegos, String> colTipo;
    @FXML private TableColumn<Videojuegos, String> colTitulo;

    private ObservableList<Videojuegos> listaStock;
    private FilteredList<Videojuegos> listaFiltrada;
    private VideojuegoDAO videojuegoDAO;
    private PlataformasDAO plataformasDAO;
    private GenerosDAO generosDAO;
    private List<Plataformas> listaPlataformasBD;
    private List<Generos> listaGenerosBD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        initGUI();
        actions();
    }

    private void instancias() {
        listaStock = FXCollections.observableArrayList();
        listaFiltrada = new FilteredList<>(listaStock, p -> true);
        videojuegoDAO = new VideojuegoDAO();
        plataformasDAO = new PlataformasDAO();
        generosDAO = new GenerosDAO();
    }

    private void initGUI() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVentaEstimada"));

        cargarDesplegables();

        tablaStock.setItems(listaFiltrada);
        cargarInventario();
    }

    private void actions() {
        txtBuscar.textProperty().addListener((obs, old, val) -> aplicarFiltros());
        comboPlataforma.valueProperty().addListener((obs, old, val) -> aplicarFiltros());
        comboGenero.valueProperty().addListener((obs, old, val) -> aplicarFiltros());
        comboEstado.valueProperty().addListener((obs, old, val) -> aplicarFiltros());
        comboStock.valueProperty().addListener((obs, old, val) -> aplicarFiltros());

        btnLimpiar.setOnAction(event -> {
            txtBuscar.clear();
            comboPlataforma.setValue("PLATAFORMAS");
            comboGenero.setValue("GÉNEROS");
            comboEstado.setValue("ESTADO");
            comboStock.setValue("PROCEDENCIA");
        });

        btnMenu.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retrojuegos/retrojuegos/main-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnMenu.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("MENÚ PRINCIPAL");
            } catch (IOException e) {
                System.out.println("Error al volver al menú principal");
            }
        });

        exportarXML.setOnAction(event->ejecutarExportacion());

    }

    private void cargarInventario() {
        try {
            listaStock.addAll(videojuegoDAO.obtenerDisponibles());
        } catch (SQLException e) {
            System.out.println("Error al cargar el stock");
        }
    }

    private void cargarDesplegables() {
        try {
            listaPlataformasBD = plataformasDAO.todasPlataformas();
            comboPlataforma.getItems().add("PLATAFORMAS");
            for (Plataformas p : listaPlataformasBD) {
                comboPlataforma.getItems().add(p.getNombre());
            }
            comboPlataforma.setValue("PLATAFORMAS");

            listaGenerosBD = generosDAO.todasGeneros();
            comboGenero.getItems().add("GÉNEROS");
            for (Generos g : listaGenerosBD) {
                comboGenero.getItems().add(g.getNombre());
            }
            comboGenero.setValue("GÉNEROS");

            comboEstado.getItems().add("ESTADO");
            for (EstadoJuego e : EstadoJuego.values()) {
                comboEstado.getItems().add(e.name().replace("_", " "));
            }
            comboEstado.setValue("ESTADO");

            comboStock.getItems().add("PROCEDENCIA");
            for (TipoStock t : TipoStock.values()) {
                comboStock.getItems().add(t.name().replace("_", " "));
            }
            comboStock.setValue("PROCEDENCIA");

        } catch (SQLException e) {
            System.out.println("No se pueden cargar los datos desde la BBDD: " + e.getMessage());
        }
    }

    private void aplicarFiltros() {
        listaFiltrada.setPredicate(juego -> {
            boolean coincideTexto = txtBuscar.getText() == null || txtBuscar.getText().isEmpty() || juego.getTitulo().toLowerCase().contains(txtBuscar.getText().toLowerCase());

            String valPlat = comboPlataforma.getValue();
            boolean coincidePlataforma = valPlat == null || valPlat.equals("PLATAFORMAS") || listaPlataformasBD.stream().anyMatch(p -> p.getNombre().equals(valPlat) && p.getIdPlataforma() == juego.getIdPlataforma());

            String valGen = comboGenero.getValue();
            boolean coincideGenero = valGen == null || valGen.equals("GÉNEROS") || listaGenerosBD.stream().anyMatch(g -> g.getNombre().equals(valGen) && g.getIdGenero() == juego.getIdGenero());

            String valEst = comboEstado.getValue();
            boolean coincideEstado = valEst == null || valEst.equals("ESTADO") || juego.getEstado().name().replace("_", " ").equals(valEst);

            String valTipo = comboStock.getValue();
            boolean coincideTipo = valTipo == null || valTipo.equals("PROCEDENCIA") || juego.getTipo().name().replace("_", " ").equals(valTipo);

            return coincideTexto && coincidePlataforma && coincideGenero && coincideEstado && coincideTipo;
        });
    }

    private void ejecutarExportacion() {
        try {
            Connection conn = DBConnection.getConnection();
            ExportadorXML exportador = new ExportadorXML();
            exportador.exportarInventario(conn);

            System.out.println("Inventario exportado correctamente a XML.");

        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (Exception e) {

            System.out.println("Error al generar el XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}