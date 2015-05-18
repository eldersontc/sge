package com.sge.modulos.finanzas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemEntradaCajaDAO extends BaseDAO {
    
    public int EliminarItemEntradaCajaPorEntradaCaja(int idEntradaCaja) {
        String sql = "DELETE FROM Finanzas.ItemEntradaCaja WHERE idEntradaCaja = " + idEntradaCaja;
        return super.Ejecutar(sql);
    }
}
