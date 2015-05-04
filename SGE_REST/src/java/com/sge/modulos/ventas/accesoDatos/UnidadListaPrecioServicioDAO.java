package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class UnidadListaPrecioServicioDAO extends BaseDAO {
    
    public int EliminarUnidadListaPrecioServicio(int idUnidadListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.UnidadListaPrecioServicio WHERE idUnidadListaPrecioServicio = " + idUnidadListaPrecioServicio;
        return super.Ejecutar(sql);
    }

    public int EliminarUnidadListaPrecioServicioPorIdListaPrecioServicio(int idListaPrecioServicio) {
        String sql = String.format("DELETE FROM Ventas.UnidadListaPrecioServicio "
                + "WHERE idItemListaPrecioServicio IN "
                + "(SELECT idItemListaPrecioServicio FROM Ventas.ItemListaPrecioServicio "
                + "WHERE idListaPrecioServicio = %d)", idListaPrecioServicio);
        return super.Ejecutar(sql);
    }

    public int EliminarUnidadListaPrecioServicioPorIdItemListaPrecioServicio(int idItemListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.UnidadListaPrecioServicio WHERE idItemListaPrecioServicio = " + idItemListaPrecioServicio;
        return super.Ejecutar(sql);
    }
}
