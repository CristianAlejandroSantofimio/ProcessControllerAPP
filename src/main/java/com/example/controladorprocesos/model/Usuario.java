package com.example.controladorprocesos.model;

public class Usuario {
    private String nombreUsuario;
    private String contrasenia;
    private final String administrador = "Bryan";
    private TipoUsuario tipoUsuario;

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        if(nombreUsuario == administrador){
            this.tipoUsuario = TipoUsuario.ADMINISTRADOR;
        }else{
            this.tipoUsuario = TipoUsuario.USUARIO_REGULAR;
        }
    }

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}