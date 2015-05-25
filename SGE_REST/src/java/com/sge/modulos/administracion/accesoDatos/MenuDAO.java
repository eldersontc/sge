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
                    "	Menu.idMenu, Menu.idMenuPadre, Menu.nombre, Menu.formulario, Menu.icono \n" +
                    "FROM \n" +
                    "	Administracion.Usuario AS Usuario INNER JOIN \n" +
                    "	Administracion.PerfilMenu AS PerfilMenu ON Usuario.idPerfil = PerfilMenu.idPerfil INNER JOIN \n" +
                    "	Administracion.Menu AS Menu ON PerfilMenu.idMenu = Menu.idMenu \n" +
                    "WHERE \n" +
                    "	Usuario.idUsuario = " + idUsuario;
        return super.ObtenerLista(sql);
    }
}
