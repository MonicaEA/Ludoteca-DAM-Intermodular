package com.retrojuegos.retrojuegos.Service;

import com.retrojuegos.retrojuegos.dao.VentasDAO;
import com.retrojuegos.retrojuegos.model.TipoStock;

import java.sql.SQLException;

public class FinanzasService {

    private VentasDAO ventasDAO = new VentasDAO();

    public double[] calcularRepartoBeneficios() throws SQLException {
        double beneficioComun = ventasDAO.obtenerBeneficioNeto(TipoStock.COMUN.name());
        double beneficioPropio = ventasDAO.obtenerBeneficioNeto(TipoStock.PROPIO.name());

        double totalMoni= (beneficioComun*0.5)+(beneficioPropio*0.70);
        double totalBetty = (beneficioComun*0.5)+(beneficioPropio*0.30);
        return new double[]{totalMoni,totalBetty};
    }



}
