package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioProductoDAO extends BaseDAO {
    
    public List<Object[]> ObtenerListasPrecio(String filtro) {
        String sql = "SELECT \n"
                + "ListaPrecioProducto.idListaPrecioProducto, ListaPrecioProducto.nombre, ListaPrecioProducto.activo \n"
                + "FROM \n"
                + "Ventas.ListaPrecioProducto " + filtro;
        return super.ObtenerLista(sql);
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
