package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.MenuDAO;
import com.sge.modulos.administracion.entidades.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDTO {

    MenuDAO menuDAO;

    private List<Menu> ObtenerSubMenus(List<Menu> menus, int idMenuPadre) {
        List<Menu> lista = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getIdMenuPadre() == idMenuPadre) {
                Menu menuNuevo = new Menu();
                menuNuevo.setIdMenu(menu.getIdMenu());
                menuNuevo.setIdMenuPadre(menu.getIdMenuPadre());
                menuNuevo.setNombre(menu.getNombre());
                menuNuevo.setFormulario(menu.getFormulario());
                menuNuevo.setIcono(menu.getIcono());
                menuNuevo.setSubMenus(ObtenerSubMenus(menus, menu.getIdMenu()));
                lista.add(menuNuevo);
            }
        }
        return lista;
    }

    private List<Menu> ObtenerMenus(List<Menu> menus) {
        List<Menu> lista = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getIdMenuPadre()== 0) {
                Menu menuNuevo = new Menu();
                menuNuevo.setIdMenu(menu.getIdMenu());
                menuNuevo.setIdMenuPadre(menu.getIdMenuPadre());
                menuNuevo.setNombre(menu.getNombre());
                menuNuevo.setFormulario(menu.getFormulario());
                menuNuevo.setIcono(menu.getIcono());
                menuNuevo.setSubMenus(ObtenerSubMenus(menus, menu.getIdMenu()));
                lista.add(menuNuevo);
            }
        }
        return lista;
    }

    public List<Menu> ObtenerMenusPorUsuario(int idUsuario) {
        List<Menu> lista;
        try {
            menuDAO = new MenuDAO();
            menuDAO.AbrirSesion();
            lista = ObtenerMenus(menuDAO.ObtenerMenusPorUsuario(idUsuario));
        } catch (Exception e) {
            throw e;
        } finally {
            menuDAO.CerrarSesion();
        }
        return lista;
    }
    
    public List<Menu> ObtenerMenusPorPerfil(int idPerfil) {
        List<Menu> lista;
        try {
            menuDAO = new MenuDAO();
            menuDAO.AbrirSesion();
            lista = ObtenerMenus(menuDAO.ObtenerMenusPorPerfil(idPerfil));
        } catch (Exception e) {
            throw e;
        } finally {
            menuDAO.CerrarSesion();
        }
        return lista;
    }
}
