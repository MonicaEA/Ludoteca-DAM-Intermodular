package com.retrojuegos.retrojuegos.Service;
import com.retrojuegos.retrojuegos.model.*;


public class UsuarioActualService {

    private static Usuarios usuarioActivo;

    public static void conectar(Usuarios usuario) {
        usuarioActivo = usuario;
    }

    public static Usuarios identificar() {
        return usuarioActivo;
    }

    public static void cerrarSesion() {
        usuarioActivo = null;
    }


}
