package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDAO extends AdministracionDAO{
    
    public List<Object[]> ObtenerMenus(int idUsuario){
        String sql = "SELECT \n" +
                        "Menu.idMenu, Menu.idMenuPadre, Menu.nombre \n" +
                     "FROM \n" +
                        "Administracion.PerfilUsuario AS PerfilUsuario INNER JOIN \n" +
                        "Administracion.Menu AS Menu INNER JOIN \n" +
                        "Administracion.PerfilMenu AS PerfilMenu ON Menu.idMenu = PerfilMenu.idMenu ON PerfilUsuario.idPerfil = PerfilMenu.idPerfil \n" +
                     "WHERE \n" +
                        "PerfilUsuario.idUsuario = " + idUsuario;
        return super.ObtenerLista(sql);
    }
}
