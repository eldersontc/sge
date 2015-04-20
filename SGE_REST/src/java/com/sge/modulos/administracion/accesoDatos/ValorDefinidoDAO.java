package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ValorDefinidoDAO extends BaseDAO {

    public List<Object[]> ObtenerValoresDefinidos(String filtro) {
        String sql = "SELECT \n"
                + "ValorDefinido.idValorDefinido, ValorDefinido.usuario, Entidad.nombre AS entidad, ValorDefinido.activo \n"
                + "FROM \n"
                + "Administracion.ValorDefinido AS ValorDefinido \n"
                + "INNER JOIN Administracion.Entidad AS Entidad ON ValorDefinido.idEntidad = Entidad.idEntidad " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarValorDefinido(int idValorDefinido, int idUsuario, String usuario, int idEntidad, String json, boolean activo) {
        String sql = String.format("UPDATE Administracion.ValorDefinido SET idUsuario = '%d', usuario = '%s', idEntidad = %d, json = '%s', activo = %b WHERE idValorDefinido = %d", idUsuario, usuario, idEntidad, json, activo, idValorDefinido);
        return super.Ejecutar(sql);
    }

    public int EliminarValorDefinido(int idValorDefinido) {
        String sql = "DELETE FROM Administracion.ValorDefinido WHERE idValorDefinido = " + idValorDefinido;
        return super.Ejecutar(sql);
    }
}
