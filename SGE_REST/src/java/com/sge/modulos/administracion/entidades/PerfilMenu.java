package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class PerfilMenu {
    
    private int idPerfilMenu;
    private int idPerfil;
    private int idMenu;
    private String filtro;

    public PerfilMenu(int idPerfil, int idMenu, String filtro) {
        this.idPerfil = idPerfil;
        this.idMenu = idMenu;
        this.filtro = filtro;
    }

    public PerfilMenu(int idPerfil, int idMenu) {
        this.idPerfil = idPerfil;
        this.idMenu = idMenu;
    }

    public int getIdPerfilMenu() {
        return idPerfilMenu;
    }

    public void setIdPerfilMenu(int idPerfilMenu) {
        this.idPerfilMenu = idPerfilMenu;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}
