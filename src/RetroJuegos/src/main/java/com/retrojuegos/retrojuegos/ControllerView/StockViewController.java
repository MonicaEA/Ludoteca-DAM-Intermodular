package com.retrojuegos.retrojuegos.ControllerView;
import com.retrojuegos.retrojuegos.Service.NavegacionMenuService;
import com.retrojuegos.retrojuegos.Service.StockService;
import com.retrojuegos.retrojuegos.dao.GenerosDAO;
import com.retrojuegos.retrojuegos.dao.PlataformasDAO;
import com.retrojuegos.retrojuegos.dao.VideojuegoDAO;
import com.retrojuegos.retrojuegos.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
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
    private StockService stockService = new StockService();

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
        stockService = new StockService();
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

        btnMenu.setOnAction(event -> NavegacionMenuService.irAMenuPrincipal(btnMenu));

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
        listaFiltrada.setPredicate(juego -> stockService.cumpleFiltros(
                juego,
                txtBuscar.getText(),
                comboPlataforma.getValue(),
                comboGenero.getValue(),
                comboEstado.getValue(),
                comboStock.getValue(),
                listaPlataformasBD,
                listaGenerosBD
        ));
    }

    private void ejecutarExportacion() {
        try {
            stockService.exportarInventarioaXML();
            mostrarAlerta("Exportado","Se ha generado el archivo correctamente");

        } catch (Exception e) {
            mostrarAlerta("Error","No se ha podido generar el archivo: "+e.getMessage());
        }
    }

    public void mostrarAlerta(String titulo,String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}