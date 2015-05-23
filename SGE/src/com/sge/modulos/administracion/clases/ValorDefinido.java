package com.sge.modulos.administracion.clases;

/**
 *
 * @author elderson
 */
public class ValorDefinido {
    
    private int idValorDefinido;
    private int idUsuario;
    private String usuario;
    private int idEntidad;
    private String nombreEntidad;
    private String json;
    private boolean activo;

    public ValorDefinido() {
    }

    public int getIdValorDefinido() {
        return idValorDefinido;
    }

    public void setIdValorDefinido(int idValorDefinido) {
        this.idValorDefinido = idValorDefinido;
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

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
