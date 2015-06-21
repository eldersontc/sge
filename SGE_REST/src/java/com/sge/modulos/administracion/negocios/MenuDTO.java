package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.MenuDAO;
import com.sge.modulos.administracion.accesoDatos.PerfilMenuDAO;
import com.sge.modulos.administracion.entidades.Menu;
import com.sge.modulos.administracion.entidades.PerfilMenu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDTO extends BaseDTO {

    MenuDAO menuDAO;
    PerfilMenuDAO perfilMenuDAO;

    public MenuDTO(int idUsuario) {
        super(idUsuario);
    }

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
                menuNuevo.setFiltro(menu.getFiltro());
                menuNuevo.setCheck(menu.isCheck());
                menuNuevo.setNuevoCheck(menu.isCheck());
                menuNuevo.setSubMenus(ObtenerSubMenus(menus, menu.getIdMenu()));
                lista.add(menuNuevo);
            }
        }
        return lista;
    }

    private List<Menu> ObtenerMenus(List<Menu> menus) {
        List<Menu> lista = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getIdMenuPadre() == 0) {
                Menu menuNuevo = new Menu();
                menuNuevo.setIdMenu(menu.getIdMenu());
                menuNuevo.setIdMenuPadre(menu.getIdMenuPadre());
                menuNuevo.setNombre(menu.getNombre());
                menuNuevo.setFormulario(menu.getFormulario());
                menuNuevo.setIcono(menu.getIcono());
                menuNuevo.setFiltro(menu.getFiltro());
                menuNuevo.setCheck(menu.isCheck());
                menuNuevo.setNuevoCheck(menu.isCheck());
                menuNuevo.setSubMenus(ObtenerSubMenus(menus, menu.getIdMenu()));
                lista.add(menuNuevo);
            }
        }
        return lista;
    }

    public List<Menu> ObtenerMenusPorUsuario() {
        List<Menu> lista;
        try {
            menuDAO = new MenuDAO();
            menuDAO.AbrirSesion();
            lista = ObtenerMenus(menuDAO.ObtenerMenusPorUsuario(getIdUsuario()));
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

    public boolean ActualizarPermisos(Menu[] menus, int idPerfil) {
        try {
            perfilMenuDAO = new PerfilMenuDAO();
            perfilMenuDAO.IniciarTransaccion();
            for (Menu menu : menus) {
                if (menu.isAgregar()) {
                    perfilMenuDAO.Agregar(new PerfilMenu(idPerfil, menu.getIdMenu(), menu.getFiltro()));
                }
                if (menu.isEliminar()) {
                    perfilMenuDAO.EliminarPerfilMenu(idPerfil, menu.getIdMenu());
                }
            }
            perfilMenuDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            perfilMenuDAO.AbortarTransaccion();
            throw e;
        } finally {
            perfilMenuDAO.CerrarSesion();
        }
        return true;
    }
}
