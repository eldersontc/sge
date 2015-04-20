package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoUnidadDAO extends BaseDAO {
    
    public List<Object[]> ObtenerProductoUnidadesPorIdProducto(int idProducto) {
        String sql = "SELECT \n"
                + "ProductoUnidad.idProductoUnidad, Unidad.idUnidad AS idUnidad, Unidad.abreviacion AS abreviacionUnidad, \n"
                + "ProductoUnidad.factor \n"
                + "FROM \n"
                + "Inventarios.ProductoUnidad AS ProductoUnidad INNER JOIN Inventarios.Unidad AS Unidad ON ProductoUnidad.idUnidad = Unidad.idUnidad \n"
                + "WHERE ProductoUnidad.idProducto = " + idProducto;
        return super.ObtenerLista(sql);
    }
    
    public int ActualizarProductoUnidad(int idProductoUnidad, int factor) {
        String sql = String.format("UPDATE Inventarios.ProductoUnidad SET factor = %d WHERE idProductoUnidad = %d", factor, idProductoUnidad);
        return super.Ejecutar(sql);
    }
    
    public int EliminarProductoUnidad(int idProductoUnidad) {
        String sql = "DELETE FROM Inventarios.ProductoUnidad WHERE idProductoUnidad = " + idProductoUnidad;
        return super.Ejecutar(sql);
    }
    
    public int EliminarProductoUnidadPorIdProducto(int idProducto) {
        String sql = "DELETE FROM Inventarios.ProductoUnidad WHERE idProducto = " + idProducto;
        return super.Ejecutar(sql);
    }
}
