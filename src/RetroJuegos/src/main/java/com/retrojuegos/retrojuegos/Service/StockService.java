package com.retrojuegos.retrojuegos.Service;

import com.retrojuegos.retrojuegos.database.DBConnection;
import com.retrojuegos.retrojuegos.model.Generos;
import com.retrojuegos.retrojuegos.model.Plataformas;
import com.retrojuegos.retrojuegos.model.Videojuegos;
import com.retrojuegos.retrojuegos.util.ExportadorXML;

import java.sql.Connection;
import java.util.List;

public class StockService {

    public boolean cumpleFiltros(Videojuegos juego, String texto, String plat, String gen, String est, String stock, List<Plataformas> plataformas, List<Generos> generos) {

        boolean coincideTexto = texto == null || texto.isEmpty() ||
                juego.getTitulo().toLowerCase().contains(texto.toLowerCase());

        boolean coincidePlat = plat == null || plat.equals("PLATAFORMAS") ||
                plataformas.stream().anyMatch(p -> p.getNombre().equals(plat) && p.getIdPlataforma() == juego.getIdPlataforma());

        boolean coincideGen = gen == null || gen.equals("GÉNEROS") ||
                generos.stream().anyMatch(g -> g.getNombre().equals(gen) && g.getIdGenero() == juego.getIdGenero());

        boolean coincideEst = est == null || est.equals("ESTADO") ||
                juego.getEstado().name().replace("_", " ").equals(est);

        boolean coincideTipo = stock == null || stock.equals("PROCEDENCIA") ||
                juego.getTipo().name().replace("_", " ").equals(stock);

        return coincideTexto && coincidePlat && coincideGen && coincideEst && coincideTipo;
    }


    public void exportarInventarioaXML() {
        try {
            Connection connection = DBConnection.getConnection();

            ExportadorXML exportadorXML = new ExportadorXML();
            exportadorXML.exportarInventario(connection);

        } catch (Exception e) {
            System.out.println("Error en la exportación: " + e.getMessage());
        }

    }
}