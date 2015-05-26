package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Menu;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDAO extends BaseDAO {

    public List<Menu> ObtenerMenusPorUsuario(int idUsuario) {
        String sql = "SELECT \n"
                + "	Menu.*, PerfilMenu.filtro, CAST(TRUE AS boolean) AS check \n"
                + "FROM \n"
                + "	Administracion.Usuario AS Usuario INNER JOIN \n"
                + "	Administracion.PerfilMenu AS PerfilMenu ON Usuario.idPerfil = PerfilMenu.idPerfil INNER JOIN \n"
                + "	Administracion.Menu AS Menu ON PerfilMenu.idMenu = Menu.idMenu \n"
                + "WHERE \n"
                + "	Usuario.idUsuario = " + idUsuario;
        return super.ObtenerLista(sql, Menu.class);
    }

    public List<Menu> ObtenerMenusPorPerfil(int idPerfil) {
        String sql = "SELECT \n"
                + "	Menu.*, PerfilMenu.filtro, \n"
                + "	CAST(CASE WHEN PerfilMenu.idPerfilMenu ISNULL THEN FALSE ELSE TRUE END AS boolean) AS check \n"
                + "FROM \n"
                + "	Administracion.PerfilMenu AS PerfilMenu RIGHT JOIN \n"
                + "	Administracion.Menu AS Menu \n"
                + "	ON PerfilMenu.idMenu = Menu.idMenu AND PerfilMenu.idPerfil = " + idPerfil;
        return super.ObtenerLista(sql, Menu.class);
    }
}
