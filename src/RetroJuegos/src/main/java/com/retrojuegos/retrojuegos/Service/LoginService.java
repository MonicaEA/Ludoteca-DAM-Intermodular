package com.retrojuegos.retrojuegos.Service;

import com.retrojuegos.retrojuegos.dao.UsuarioDAO;
import com.retrojuegos.retrojuegos.model.Usuarios;

import java.sql.SQLException;

public class LoginService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuarios autenticar(String dni, String pass) throws SQLException, IllegalArgumentException {

        if (dni == null || dni.isEmpty() || pass == null || pass.isEmpty()) {
            throw new IllegalArgumentException("DNI y contraseña son obligatorios");
        }

        Usuarios user = usuarioDAO.validarLogin(dni, pass);

        if (user == null) {
            throw new IllegalArgumentException("DNI o contraseña incorrectos");
        }

        return user;
    }
}