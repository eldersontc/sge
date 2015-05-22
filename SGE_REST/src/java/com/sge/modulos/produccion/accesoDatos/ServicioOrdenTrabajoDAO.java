package com.sge.modulos.produccion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ServicioOrdenTrabajoDAO extends BaseDAO {
    
    public int EliminarServicioOrdenTrabajoPorIdItemOrdenTrabajo(int idItemOrdenTrabajo) {
        String sql = "DELETE FROM Produccion.ServicioOrdenTrabajo WHERE idItemOrdenTrabajo = " + idItemOrdenTrabajo;
        return super.Ejecutar(sql);
    }
}
