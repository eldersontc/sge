package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Entidad;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntidadDAO extends BaseDAO {

    public List<Entidad> ObtenerEntidades(String filtro) {
        String sql = "SELECT \n"
                + "Entidad.* \n"
                + "FROM \n"
                + "Administracion.Entidad AS Entidad " + filtro;
        return super.ObtenerLista(sql, Entidad.class);
    }
}
