package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Distrito;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DistritoDAO extends BaseDAO {

    public List<Distrito> ObtenerDistritos(String filtro) {
        String sql = "SELECT \n"
                + "Distrito.* \n"
                + "FROM \n"
                + "Administracion.Distrito AS Distrito " + filtro;
        return super.ObtenerLista(sql, Distrito.class);
    }
}
