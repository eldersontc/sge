package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class PerfllMenu {
    
    private int idPerfilMenu;
    private Perfil perfil;
    private Menu menu;

    public PerfllMenu() {
    }

    public int getIdPerfilMenu() {
        return idPerfilMenu;
    }

    public void setIdPerfilMenu(int idPerfilMenu) {
        this.idPerfilMenu = idPerfilMenu;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
}
