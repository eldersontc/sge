package com.sge.modulos.facturacion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemFacturaDAO extends BaseDAO {
    
    public int EliminarItemFacturaPorFactura(int idFactura) {
        String sql = "DELETE FROM Facturacion.ItemFactura WHERE idFactura = " + idFactura;
        return super.Ejecutar(sql);
    }
}
