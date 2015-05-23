package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Moneda;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MonedaDAO extends BaseDAO {

    public List<Moneda> ObtenerMonedas(String filtro) {
        String sql = "SELECT \n"
                + "Moneda.* \n"
                + "FROM \n"
                + "Administracion.Moneda AS Moneda " + filtro;
        return super.ObtenerLista(sql, Moneda.class);
    }

    public int ActualizarMoneda(int idMoneda, String simbolo, String nombre, boolean activo) {
        String sql = String.format("UPDATE Administracion.Moneda SET simbolo = '%s', nombre = '%s', activo = %b WHERE idMoneda = %d", simbolo, nombre, activo, idMoneda);
        return super.Ejecutar(sql);
    }

    public int EliminarMoneda(int idMoneda) {
        String sql = "DELETE FROM Administracion.Moneda WHERE idMoneda = " + idMoneda;
        return super.Ejecutar(sql);
    }
}
