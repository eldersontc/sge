package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProvinciaDAO extends BaseDAO {
    
    public List<Object[]> ObtenerProvincias(String filtro) {
        String sql = "SELECT \n"
                + "Provincia.idProvincia, Provincia.nombre \n"
                + "FROM \n"
                + "Administracion.Provincia \n" + filtro;
        return super.ObtenerLista(sql);
    }
}
