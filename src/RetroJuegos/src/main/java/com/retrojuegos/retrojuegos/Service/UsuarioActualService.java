package com.retrojuegos.retrojuegos.Service;
import com.retrojuegos.retrojuegos.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


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

    public static boolean esAdmin() {
        return usuarioActivo != null &&
                usuarioActivo.getPerfil().name().equals("ADMINISTRADOR");
    }
}
