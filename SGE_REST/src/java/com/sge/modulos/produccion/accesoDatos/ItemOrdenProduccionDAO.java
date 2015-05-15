package com.sge.modulos.produccion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemOrdenProduccionDAO extends BaseDAO {

    public int EliminarItemOrdenProduccion(int idItemOrdenProduccion) {
        String sql = "DELETE FROM Produccion.ItemOrdenProduccion WHERE idItemOrdenProduccion = " + idItemOrdenProduccion;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemOrdenProduccionPorOrdenProduccion(int idOrdenProduccion) {
        String sql = "DELETE FROM Produccion.ItemOrdenProduccion WHERE idOrdenProduccion = " + idOrdenProduccion;
        return super.Ejecutar(sql);
    }
}
