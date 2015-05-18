package com.sge.modulos.facturacion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.facturacion.entidades.Factura;
import java.util.List;

/**
 *
 * @author elderson
 */
public class FacturaDAO extends BaseDAO {
    
    public List<Factura> ObtenerFacturas(String filtro) {
        String sql = "SELECT \n"
                + "Factura.* \n"
                + "FROM \n"
                + "Facturacion.Factura " + filtro;
        return super.ObtenerLista(sql, Factura.class);
    }

    public int EliminarFactura(int idFactura) {
        String sql = "DELETE FROM Facturacion.Factura WHERE idFactura = " + idFactura;
        return super.Ejecutar(sql);
    }
}
