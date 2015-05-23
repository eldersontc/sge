package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Provincia;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProvinciaDAO extends BaseDAO {

    public List<Provincia> ObtenerProvincias(String filtro) {
        String sql = "SELECT \n"
                + "Provincia.* \n"
                + "FROM \n"
                + "Administracion.Provincia AS Provincia \n" + filtro;
        return super.ObtenerLista(sql, Provincia.class);
    }
}
