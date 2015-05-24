package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ListaPrecioServicio;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioServicioDAO extends BaseDAO {
    
    public List<ListaPrecioServicio> ObtenerListasPrecio(String filtro) {
        String sql = "SELECT \n"
                + "ListaPrecioServicio.* \n"
                + "FROM \n"
                + "Ventas.ListaPrecioServicio AS ListaPrecioServicio " + filtro;
        return super.ObtenerLista(sql, ListaPrecioServicio.class);
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
