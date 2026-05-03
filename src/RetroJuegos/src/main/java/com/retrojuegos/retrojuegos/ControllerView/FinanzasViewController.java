package com.retrojuegos.retrojuegos.ControllerView;
import com.retrojuegos.retrojuegos.Service.FinanzasService;
import com.retrojuegos.retrojuegos.Service.NavegacionMenuService;
import com.retrojuegos.retrojuegos.dao.ComprasDAO;
import com.retrojuegos.retrojuegos.dao.VentasDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FinanzasViewController implements Initializable {

    @FXML
    private Label lblGastado;
    @FXML
    private Label lblIngresado;
    @FXML
    private Label lblBetty;
    @FXML
    private Label lnblMoni;
    @FXML
    private Button btnVolver;

    private ComprasDAO comprasDAO;
    private VentasDAO ventasDAO;
    private FinanzasService finanzasService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        initGUI();
        acciones();
    }

    private void instancias() {
        comprasDAO = new ComprasDAO();
        ventasDAO = new VentasDAO();
        finanzasService = new FinanzasService();
    }

    private void initGUI() {
        calcularFinanzas();
    }

    private void acciones() {
        btnVolver.setOnAction(event -> NavegacionMenuService.irAMenuPrincipal(btnVolver));
    }

        private void calcularFinanzas () {
            try {
                double totalGastado = comprasDAO.obtenerSumaTotalCompras();
                double totalIngresado = ventasDAO.obtenerSumaTotalVentas();

                double[] beneficios = finanzasService.calcularRepartoBeneficios();

                lblGastado.setText(String.format("TOTAL GASTADO: %.2f €", totalGastado));
                lblIngresado.setText(String.format("TOTAL INGRESADO: %.2f €", totalIngresado));
                lnblMoni.setText(String.format("Beneficios Moni: %.2f €", beneficios[0]));
                lblBetty.setText(String.format("Beneficios Betty: %.2f €", beneficios[1]));

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
