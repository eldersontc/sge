package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioMaquinaDAO extends BaseDAO {
    
    public List<Object[]> ObtenerListasPrecio(String filtro) {
        String sql = "SELECT \n"
                + "ListaPrecioMaquina.idListaPrecioMaquina, ListaPrecioMaquina.nombre, ListaPrecioMaquina.activo \n"
                + "FROM \n"
                + "Ventas.ListaPrecioMaquina " + filtro;
        return super.ObtenerLista(sql);
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
