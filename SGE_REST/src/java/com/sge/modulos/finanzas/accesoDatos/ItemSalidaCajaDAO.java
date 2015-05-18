package com.sge.modulos.finanzas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemSalidaCajaDAO extends BaseDAO {
    
    public int EliminarItemSalidaCajaPorSalidaCaja(int idSalidaCaja) {
        String sql = "DELETE FROM Finanzas.ItemSalidaCaja WHERE idSalidaCaja = " + idSalidaCaja;
        return super.Ejecutar(sql);
    }
}
