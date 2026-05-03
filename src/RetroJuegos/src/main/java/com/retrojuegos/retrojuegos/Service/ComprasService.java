package com.retrojuegos.retrojuegos.Service;
import com.retrojuegos.retrojuegos.dao.ClientesDAO;
import com.retrojuegos.retrojuegos.dao.ComprasDAO;
import com.retrojuegos.retrojuegos.dao.VideojuegoDAO;
import com.retrojuegos.retrojuegos.model.Clientes;
import com.retrojuegos.retrojuegos.model.Compras;
import com.retrojuegos.retrojuegos.model.Usuarios;
import com.retrojuegos.retrojuegos.model.Videojuegos;
import java.sql.SQLException;
import java.time.LocalDate;

public class ComprasService {
    private ClientesDAO clientesDAO = new ClientesDAO();
    private VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    private ComprasDAO comprasDAO = new ComprasDAO();

    // Este método centraliza TODA la lógica que antes estaba en el Controller
    public void procesarNuevaCompra(Clientes cliente, Videojuegos videojuego, Usuarios usuario) throws SQLException {

        Clientes clienteExistente = clientesDAO.buscarPorTelefono(cliente.getTelefono());
        if (clienteExistente == null) {
            clientesDAO.insertarCliente(cliente);
            clienteExistente = clientesDAO.buscarPorTelefono(cliente.getTelefono());
        }

        videojuego.setUsuarioRegistro(usuario.getIdUsuario());
        int idJuego = videojuegoDAO.insertarJuego(videojuego);


        Compras compra = new Compras(0, LocalDate.now(), idJuego, usuario.getIdUsuario(), clienteExistente.getIdCliente());
        comprasDAO.insertarCompra(compra);
    }
}

