package com.sge.modulos.facturacion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemGuiaRemisionDAO extends BaseDAO {
    
    public int EliminarItemGuiaRemision(int idItemGuiaRemision) {
        String sql = "DELETE FROM Facturacion.ItemGuiaRemision WHERE idItemGuiaRemision = " + idItemGuiaRemision;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemGuiaRemisionPorGuiaRemision(int idGuiaRemision) {
        String sql = "DELETE FROM Facturacion.ItemGuiaRemision WHERE idGuiaRemision = " + idGuiaRemision;
        return super.Ejecutar(sql);
    }
}
