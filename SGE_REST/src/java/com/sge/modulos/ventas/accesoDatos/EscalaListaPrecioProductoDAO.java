package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioProducto;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioProductoDAO extends BaseDAO {
    
    public List<EscalaListaPrecioProducto> ObtenerEscalasPorProducto(int idListaPrecioProducto, int idProducto, int idUnidad){
        String sql = String.format("SELECT \n" +
                                    "  EscalaListaPrecioProducto.* \n" +
                                    "FROM \n" +
                                    "  Ventas.EscalaListaPrecioProducto AS EscalaListaPrecioProducto \n" +
                                    "  INNER JOIN Ventas.UnidadListaPrecioProducto AS UnidadListaPrecioProducto \n" +
                                    "  ON EscalaListaPrecioProducto.idUnidadListaPrecioProducto = UnidadListaPrecioProducto.idUnidadListaPrecioProducto \n" +
                                    "  INNER JOIN Ventas.ItemListaPrecioProducto AS ItemListaPrecioProducto \n" +
                                    "  ON UnidadListaPrecioProducto.idItemListaPrecioProducto = ItemListaPrecioProducto.idItemListaPrecioProducto\n" +
                                    "  INNER JOIN Inventarios.ProductoUnidad AS ProductoUnidad \n" +
                                    "  ON UnidadListaPrecioProducto.idProductoUnidad = ProductoUnidad.idProductoUnidad\n" +
                                    "WHERE \n" +
                                    "  ItemListaPrecioProducto.idListaPrecioProducto = %d AND \n" +
                                    "  ItemListaPrecioProducto.idProducto = %d AND \n" +
                                    "  ProductoUnidad.idUnidad = %d", idListaPrecioProducto, idProducto, idUnidad);
        return super.ObtenerLista(sql, EscalaListaPrecioProducto.class);
    }
    
    public int ActualizarEscalaListaPrecioProducto(int idEscalaListaPrecioProducto, int desde, int hasta, double precio) {
        String sql = String.format("UPDATE Ventas.EscalaListaPrecioProducto SET desde = %d, hasta = %d, precio = %s WHERE idEscalaListaPrecioProducto = %d", desde, hasta, precio, idEscalaListaPrecioProducto);
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
