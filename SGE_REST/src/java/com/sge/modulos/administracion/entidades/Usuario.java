package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class Usuario {
    
    private int idUsuario;
    private String usuario;
    private String clave;
    private int idPerfil;
    private String nombrePerfil;
    private boolean activo;
    private boolean conectado;
    private String ip;
    private int mensajesSinLeer;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getMensajesSinLeer() {
        return mensajesSinLeer;
    }

    public void setMensajesSinLeer(int mensajesSinLeer) {
        this.mensajesSinLeer = mensajesSinLeer;
    }
}
