package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Departamento;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DepartamentoDAO extends BaseDAO {

    public List<Departamento> ObtenerDepartamentos(String filtro) {
        String sql = "SELECT \n"
                + "Departamento.* \n"
                + "FROM \n"
                + "Administracion.Departamento AS Departamento " + filtro;
        return super.ObtenerLista(sql, Departamento.class);
    }
}
