package com.retrojuegos.retrojuegos.ControllerView;

import com.retrojuegos.retrojuegos.model.*;





public class UsuarioActualController {

    private static Usuarios usuarioLogueado;




    public static Usuarios getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuarios u) {
        usuarioLogueado = u;
    }


}
