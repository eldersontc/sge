package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DepartamentoDAO extends BaseDAO {
    
    public List<Object[]> ObtenerDepartamentos(String filtro) {
        String sql = "SELECT \n"
                + "Departamento.idDepartamento, Departamento.nombre \n"
                + "FROM \n"
                + "Administracion.Departamento \n" + filtro;
        return super.ObtenerLista(sql);
    }
}
