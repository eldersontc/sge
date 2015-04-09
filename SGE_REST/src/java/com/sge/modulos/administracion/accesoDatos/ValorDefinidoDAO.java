package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class ValorDefinidoDAO extends AdministracionDAO {

    public List<Object[]> ObtenerValoresDefinidos(String filtro) {
        String sql = "SELECT \n"
                + "ValorDefinido.idValorDefinido, ValorDefinido.usuario, ValorDefinido.entidad, ValorDefinido.activo \n"
                + "FROM \n"
                + "Administracion.ValorDefinido " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarValorDefinido(int idValorDefinido, int idUsuario, String usuario, String entidad, String json, boolean activo) {
        String sql = String.format("UPDATE Administracion.ValorDefinido SET idUsuario = '%d', usuario = '%s', entidad = '%s', json = '%s', activo = %b WHERE idValorDefinido = %d", idUsuario, usuario, entidad, json, activo, idValorDefinido);
        return super.Ejecutar(sql);
    }

    public int EliminarValorDefinido(int idValorDefinido) {
        String sql = "DELETE FROM Administracion.ValorDefinido WHERE idValorDefinido = " + idValorDefinido;
        return super.Ejecutar(sql);
    }
}
