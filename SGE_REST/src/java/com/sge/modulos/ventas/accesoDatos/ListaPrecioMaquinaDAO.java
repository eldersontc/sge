package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ListaPrecioMaquina;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioMaquinaDAO extends BaseDAO {

    public List<ListaPrecioMaquina> ObtenerListasPrecio(String filtro) {
        String sql = "SELECT \n"
                + "ListaPrecioMaquina.* \n"
                + "FROM \n"
                + "Ventas.ListaPrecioMaquina AS ListaPrecioMaquina " + filtro;
        return super.ObtenerLista(sql, ListaPrecioMaquina.class);
    }

    public int ActualizarListaPrecioMaquina(int idListaPrecioMaquina, String nombre, boolean activo) {
        String sql = String.format("UPDATE Ventas.ListaPrecioMaquina SET nombre = '%s', activo = %b WHERE idListaPrecioMaquina = %d", nombre, activo, idListaPrecioMaquina);
        return super.Ejecutar(sql);
    }

    public int EliminarListaPrecioMaquina(int idListaPrecioMaquina) {
        String sql = "DELETE FROM Ventas.ListaPrecioMaquina WHERE idListaPrecioMaquina = " + idListaPrecioMaquina;
        return super.Ejecutar(sql);
    }
}
