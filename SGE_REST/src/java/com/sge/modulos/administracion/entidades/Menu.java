package com.sge.modulos.administracion.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class Menu {
    
    private int idMenu;
    private int idMenuPadre;
    private String nombre;
    private String formulario;
    private String icono;
    private List<Menu> subMenus;

    public Menu() {
        subMenus = new ArrayList<>();
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(int idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}
