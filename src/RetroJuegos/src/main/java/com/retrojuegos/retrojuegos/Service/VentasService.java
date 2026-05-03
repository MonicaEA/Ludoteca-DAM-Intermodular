package com.retrojuegos.retrojuegos.Service;

import com.retrojuegos.retrojuegos.dao.VentasDAO;
import com.retrojuegos.retrojuegos.model.Videojuegos;

import java.util.List;

public class VentasService {
    private VentasDAO ventasDAO = new VentasDAO();

    public boolean procesarVentaCompleta(List<Videojuegos> carrito, int idUsuario) {
        if (carrito.isEmpty()) return false;
        return ventasDAO.registrarVenta(carrito, idUsuario);
    }

    public double calcularTotalCarrito(List<Videojuegos> carrito) {
        return carrito.stream().mapToDouble(Videojuegos::getPrecioVentaEstimada).sum();
    }




}
