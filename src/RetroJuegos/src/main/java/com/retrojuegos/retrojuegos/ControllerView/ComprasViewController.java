package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.dao.*;
import com.retrojuegos.retrojuegos.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComprasViewController implements Initializable {

    @FXML
    private Button btnFinalizar;

    @FXML
    private ChoiceBox<EstadoJuego> choiceEstado;

    @FXML
    private ChoiceBox<TipoStock> choiceTipo;

    @FXML
    private ComboBox<Generos> comboGenero;

    @FXML
    private ComboBox<Plataformas> comboPlataforma;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtPrecioVenta;


    private ClientesDAO clientesDAO = new ClientesDAO();
    private VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    private ComprasDAO comprasDAO = new ComprasDAO();
    private PlataformasDAO plataformasDAO = new PlataformasDAO();
    private GenerosDAO generosDAO = new GenerosDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceEstado.getItems().setAll(EstadoJuego.values());
        choiceTipo.getItems().setAll(TipoStock.values());
        try{
            comboPlataforma.getItems().setAll(plataformasDAO.todasPlataformas());
            comboPlataforma.getSelectionModel().selectFirst();
            comboGenero.getItems().setAll(generosDAO.todasGeneros());
            comboGenero.getSelectionModel().selectFirst();
        }catch (SQLException e){
            System.out.println("Error al cargar datos: "+e.getMessage());
        }


        // para autocompletar
        txtTelefono.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean nuevoValor) {
                if (nuevoValor == false){
                    buscarCliente();
                }
            }
        });
        btnFinalizar.setOnAction(event->{
            ejecutarCompra();
        });
    }



    private void buscarCliente() {
    String movil = txtTelefono.getText();
    if (movil.isEmpty()){
        return;
    }
    try{
        Clientes clientes = clientesDAO.buscarPorTelefono(movil);
        if (clientes!=null){
            //si el cliente existe bloqueo campos.
            txtDni.setText(clientes.getDni());
            txtNombre.setText(clientes.getNombre());
            txtApellidos.setText(clientes.getApellidos());
            txtEmail.setText(clientes.getEmail());
            permitirEdicion(false);

        }else {
            txtDni.clear();
            txtNombre.clear();
            txtApellidos.clear();
            txtEmail.clear();
            permitirEdicion(true);
        }

        }catch (SQLException e){
        System.out.println("Error al buscar cliente: "+e.getMessage());
    }

    }

    private void permitirEdicion(boolean editable) {
        txtDni.setEditable(editable);
        txtNombre.setEditable(editable);
        txtApellidos.setEditable(editable);
        txtEmail.setEditable(editable);

    }

    private void ejecutarCompra() {
        try{
            Usuarios usuario = UsuarioActualController.getUsuarioLogueado();

            Clientes cliente = clientesDAO.buscarPorTelefono(txtTelefono.getText());
                if (cliente == null){
                   //TODO corregir para añadir campo TipoCliente , ahora por defecto tengo AMBOS , si da tiempo corregir
                    cliente = new Clientes(0, txtDni.getText(), txtNombre.getText(), txtApellidos.getText(), txtEmail.getText(), txtTelefono.getText(), TipoCliente.AMBOS);
                    clientesDAO.insertarCliente(cliente);
                    cliente = clientesDAO.buscarPorTelefono(txtTelefono.getText());
                }
            //meter juego
            Videojuegos videojuego = new Videojuegos(0,txtTitulo.getText(),Double.parseDouble(txtPrecioCompra.getText()),Double.parseDouble(txtPrecioVenta.getText()),1,1,choiceEstado.getValue(),choiceTipo.getValue(), usuario.getIdUsuario());
            int idJuegoGenerado = videojuegoDAO.insertarJuego(videojuego);

            // registro compra
            Compras compra = new Compras(0,java.time.LocalDate.now(),idJuegoGenerado,usuario.getIdUsuario(),usuario.getIdUsuario());
            comprasDAO.insertarCompra(compra);

            System.out.println("Compra guardada");


        }catch (SQLException e){
            System.out.println("Error en la BBDD "+e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Que los precios sean números");
        }




    }

}
