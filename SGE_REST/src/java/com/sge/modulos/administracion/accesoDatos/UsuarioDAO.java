package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class UsuarioDAO extends AdministracionDAO {

    public List<Object[]> ObtenerUsuarios(String filtro) {
        String sql = "SELECT \n"
                + "Usuario.idUsuario, Usuario.usuario, Usuario.clave, Usuario.activo \n"
                + "FROM \n"
                + "Administracion.Usuario " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarUsuario(int idUsuario, String usuario, String clave, boolean activo) {
        String sql = String.format("UPDATE Administracion.Usuario SET usuario = '%s', clave = '%s', activo = %b WHERE idUsuario = %d", usuario, clave, activo, idUsuario);
        return super.Ejecutar(sql);
    }

    public int EliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Administracion.Usuario WHERE idUsuario = " + idUsuario;
        return super.Ejecutar(sql);
    }
}
