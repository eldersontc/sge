package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDAO extends BaseDAO{
    
    public List<Object[]> ObtenerMenus(int idUsuario){
        String sql = "SELECT \n" +
                        "Menu.idMenu, Menu.idMenuPadre, Menu.nombre, Menu.formulario, Menu.icono \n" +
                     "FROM \n" +
                        "Administracion.PerfilUsuario AS PerfilUsuario INNER JOIN \n" +
                        "Administracion.Menu AS Menu INNER JOIN \n" +
                        "Administracion.PerfilMenu AS PerfilMenu ON Menu.idMenu = PerfilMenu.idMenu ON PerfilUsuario.idPerfil = PerfilMenu.idPerfil \n" +
                     "WHERE \n" +
                        "PerfilUsuario.idUsuario = " + idUsuario;
        return super.ObtenerLista(sql);
    }
}
