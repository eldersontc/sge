package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoUnidadDAO extends BaseDAO {

    public List<Object[]> ObtenerProductoUnidades(String filtro) {
        String sql = "SELECT \n"
                + "ProductoUnidad.idProductoUnidad, ProductoUnidad.idProducto, ProductoUnidad.idUnidad, ProductoUnidad.abreviacionUnidad, \n"
                + "ProductoUnidad.factor \n"
                + "FROM \n"
                + "Inventarios.ProductoUnidad " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarProductoUnidad(int idProductoUnidad, int factor, boolean base) {
        String sql = String.format("UPDATE Inventarios.ProductoUnidad SET factor = %d, base = %b WHERE idProductoUnidad = %d", factor, base, idProductoUnidad);
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
