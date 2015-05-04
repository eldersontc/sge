package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioServicioDAO extends BaseDAO {
    
    public List<Object[]> ObtenerListasPrecio(String filtro) {
        String sql = "SELECT \n"
                + "ListaPrecioServicio.idListaPrecioServicio, ListaPrecioServicio.nombre, ListaPrecioServicio.activo \n"
                + "FROM \n"
                + "Ventas.ListaPrecioServicio " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarListaPrecioServicio(int idListaPrecioServicio, String nombre, boolean activo) {
        String sql = String.format("UPDATE Ventas.ListaPrecioServicio SET nombre = '%s', activo = %b WHERE idListaPrecioServicio = %d", nombre, activo, idListaPrecioServicio);
        return super.Ejecutar(sql);
    }

    public int EliminarListaPrecioServicio(int idListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.ListaPrecioServicio WHERE idListaPrecioServicio = " + idListaPrecioServicio;
        return super.Ejecutar(sql);
    }
}
