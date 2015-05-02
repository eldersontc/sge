package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class UnidadListaPrecioProductoDAO extends BaseDAO {

    public int EliminarUnidadListaPrecioProducto(int idUnidadListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.UnidadListaPrecioProducto WHERE idUnidadListaPrecioProducto = " + idUnidadListaPrecioProducto;
        return super.Ejecutar(sql);
    }

    public int EliminarUnidadListaPrecioProductoPorIdListaPrecioProducto(int idListaPrecioProducto) {
        String sql = String.format("DELETE FROM Ventas.UnidadListaPrecioProducto "
                + "WHERE idItemListaPrecioProducto IN "
                + "(SELECT idItemListaPrecioProducto FROM Ventas.ItemListaPrecioProducto "
                + "WHERE idListaPrecioProducto = %d)", idListaPrecioProducto);
        return super.Ejecutar(sql);
    }

    public int EliminarUnidadListaPrecioProductoPorIdItemListaPrecioProducto(int idItemListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.UnidadListaPrecioProducto WHERE idItemListaPrecioProducto = " + idItemListaPrecioProducto;
        return super.Ejecutar(sql);
    }
}
