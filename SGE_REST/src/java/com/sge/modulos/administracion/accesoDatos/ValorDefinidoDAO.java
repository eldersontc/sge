package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ValorDefinidoDAO extends BaseDAO {

    public List<ValorDefinido> ObtenerValoresDefinidos(String filtro) {
        String sql = "SELECT \n"
                + "ValorDefinido.* \n"
                + "FROM \n"
                + "Administracion.ValorDefinido AS ValorDefinido " + filtro;
        return super.ObtenerLista(sql, ValorDefinido.class);
    }

    public int ActualizarValorDefinido(int idValorDefinido, int idUsuario, String usuario, int idEntidad, String nombreEntidad, String json, boolean activo) {
        String sql = String.format("UPDATE Administracion.ValorDefinido SET idUsuario = '%d', usuario = '%s', idEntidad = %d, nombreEntidad = '%s', json = '%s', activo = %b WHERE idValorDefinido = %d", idUsuario, usuario, idEntidad, nombreEntidad, json, activo, idValorDefinido);
        return super.Ejecutar(sql);
    }

    public int EliminarValorDefinido(int idValorDefinido) {
        String sql = "DELETE FROM Administracion.ValorDefinido WHERE idValorDefinido = " + idValorDefinido;
        return super.Ejecutar(sql);
    }
}
