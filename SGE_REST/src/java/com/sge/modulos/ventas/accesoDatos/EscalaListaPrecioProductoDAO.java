package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioProductoDAO extends BaseDAO {
    
    public int ActualizarEscalaListaPrecioProducto(int idEscalaListaPrecioProducto, int desde, int hasta, double precio) {
        String sql = String.format("UPDATE Ventas.EscalaListaPrecioProducto SET desde = %d, hasta = %d, precio = %d WHERE idEscalaListaPrecioProducto = %d", desde, hasta, precio, idEscalaListaPrecioProducto);
        return super.Ejecutar(sql);
    }

    public int EliminarEscalaListaPrecioProducto(int idEscalaListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.EscalaListaPrecioProducto WHERE idEscalaListaPrecioProducto = " + idEscalaListaPrecioProducto;
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioProductoPorIdListaPrecioProducto(int idListaPrecioProducto) {
        String sql = String.format("DELETE FROM Ventas.EscalaListaPrecioProducto "
                + "WHERE idUnidadListaPrecioProducto IN "
                + "(SELECT idUnidadListaPrecioProducto FROM Ventas.UnidadListaPrecioProducto "
                + "WHERE idItemListaPrecioProducto IN "
                + "(SELECT idItemListaPrecioProducto FROM Ventas.ItemListaPrecioProducto WHERE idListaPrecioProducto = %d))", idListaPrecioProducto);
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioProductoPorIdItemListaPrecioProducto(int idItemListaPrecioProducto) {
        String sql = String.format("DELETE FROM Ventas.EscalaListaPrecioProducto "
                + "WHERE idUnidadListaPrecioProducto IN "
                + "(SELECT idUnidadListaPrecioProducto FROM Ventas.UnidadListaPrecioProducto "
                + "WHERE idItemListaPrecioProducto = %d)", idItemListaPrecioProducto);
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioProductoPorIdUnidadListaPrecioProducto(int idUnidadListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.EscalaListaPrecioProducto WHERE idUnidadListaPrecioProducto = " + idUnidadListaPrecioProducto;
        return super.Ejecutar(sql);
    }
}
