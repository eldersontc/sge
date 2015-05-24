package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ListaPrecioProducto;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioProductoDAO extends BaseDAO {

    public List<ListaPrecioProducto> ObtenerListasPrecio(String filtro) {
        String sql = "SELECT \n"
                + "ListaPrecioProducto.* \n"
                + "FROM \n"
                + "Ventas.ListaPrecioProducto AS ListaPrecioProducto " + filtro;
        return super.ObtenerLista(sql, ListaPrecioProducto.class);
    }

    public int ActualizarListaPrecioProducto(int idListaPrecioProducto, String nombre, boolean activo) {
        String sql = String.format("UPDATE Ventas.ListaPrecioProducto SET nombre = '%s', activo = %b WHERE idListaPrecioProducto = %d", nombre, activo, idListaPrecioProducto);
        return super.Ejecutar(sql);
    }

    public int EliminarListaPrecioProducto(int idListaPrecioProducto) {
        String sql = "DELETE FROM Ventas.ListaPrecioProducto WHERE idListaPrecioProducto = " + idListaPrecioProducto;
        return super.Ejecutar(sql);
    }
}
