package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.MenuDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDTO {

    MenuDAO menuDAO;

    private List<Object[]> ObtenerSubMenus(List<Object[]> menus, int idMenuPadre) {
        List<Object[]> lista = new ArrayList<>();
        for (Object[] menu : menus) {
            if ((int)menu[1] == idMenuPadre) {
                lista.add(new Object[]{ menu[0], menu[1], menu[2], menu[3], menu[4], ObtenerSubMenus(menus, (int)menu[0]) });
            }
        }
        return lista;
    }

    private List<Object[]> ObtenerMenus(List<Object[]> menus) {
        List<Object[]> lista = new ArrayList<>();
        for (Object[] menu : menus) {
            if ((int)menu[1] == 0) {
                lista.add(new Object[]{ menu[0], menu[1], menu[2], menu[3], menu[4], ObtenerSubMenus(menus, (int)menu[0]) });
            }
        }
        return lista;
    }

    public List<Object[]> ObtenerMenus(int idUsuario) {
        List<Object[]> lista;
        try {
            menuDAO = new MenuDAO();
            menuDAO.AbrirSesion();
            lista = ObtenerMenus(menuDAO.ObtenerMenus(idUsuario));
        } catch (Exception e) {
            throw e;
        } finally {
            menuDAO.CerrarSesion();
        }
        return lista;
    }
}
