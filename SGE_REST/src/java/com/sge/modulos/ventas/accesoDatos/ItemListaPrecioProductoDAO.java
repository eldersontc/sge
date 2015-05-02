package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioProductoDAO extends BaseDAO {
    
    public int EliminarItemListaPrecioProducto(int idItemListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.ItemListaPrecioProducto WHERE idItemListaPrecioProducto = " + idItemListaPrecioProducto;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemListaPrecioProductoPorIdListaPrecioProducto(int idListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.ItemListaPrecioProducto WHERE idListaPrecioProducto = " + idListaPrecioProducto;
        return super.Ejecutar(sql);
    }
}
