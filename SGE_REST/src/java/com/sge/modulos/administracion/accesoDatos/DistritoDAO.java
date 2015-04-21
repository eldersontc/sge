package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DistritoDAO extends BaseDAO {
 
    public List<Object[]> ObtenerDistritos(String filtro) {
        String sql = "SELECT \n"
                + "Distrito.idDistrito, Distrito.nombre \n"
                + "FROM \n"
                + "Administracion.Distrito \n" + filtro;
        return super.ObtenerLista(sql);
    }
}
