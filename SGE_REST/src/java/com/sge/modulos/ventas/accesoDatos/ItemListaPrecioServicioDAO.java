package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioServicioDAO extends BaseDAO {
 
    public int EliminarItemListaPrecioServicio(int idItemListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.ItemListaPrecioServicio WHERE idItemListaPrecioServicio = " + idItemListaPrecioServicio;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemListaPrecioServicioPorIdListaPrecioServicio(int idListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.ItemListaPrecioServicio WHERE idListaPrecioServicio = " + idListaPrecioServicio;
        return super.Ejecutar(sql);
    }
}
