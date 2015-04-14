package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class EntidadDAO extends AdministracionDAO {
    
    public List<Object[]> ObtenerEntidades(String filtro) {
        String sql = "SELECT \n"
                + "Entidad.idEntidad, Entidad.nombre, Entidad.formulario, Entidad.activo \n"
                + "FROM \n"
                + "Administracion.Entidad \n" + filtro;
        return super.ObtenerLista(sql);
    }
}
