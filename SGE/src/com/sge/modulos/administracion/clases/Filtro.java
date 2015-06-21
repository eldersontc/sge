package com.sge.modulos.administracion.clases;

/**
 *
 * @author elderson
 */
public class Filtro {
    
    private int idFiltro;
    private int idEntidad;
    private String nombreEntidad;
    private int idUsuario;
    private String usuario;
    private String filtro;
    private boolean activo;

    public Filtro() {
    }
    
    public int getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(int idFiltro) {
        this.idFiltro = idFiltro;
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
