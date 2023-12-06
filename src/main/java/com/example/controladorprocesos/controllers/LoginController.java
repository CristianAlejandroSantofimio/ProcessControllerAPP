package com.example.controladorprocesos.controllers;

import com.example.controladorprocesos.model.Usuario;

public class LoginController {
    ModelFactoryController modelFactoryController;
    public LoginController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }
    public Usuario login(String nombreUsuario, String contraseniaUsuario) {
        return modelFactoryController.login(new Usuario(nombreUsuario,contraseniaUsuario));
    }
}
