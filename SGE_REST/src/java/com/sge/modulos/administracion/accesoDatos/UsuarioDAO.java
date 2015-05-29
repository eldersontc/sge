package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Usuario;
import java.util.List;

/**
 *
 * @author elderson
 */
public class UsuarioDAO extends BaseDAO {

    public List<Usuario> ObtenerUsuarios(String filtro) {
        String sql = "SELECT \n"
                + "Usuario.* \n"
                + "FROM \n"
                + "Administracion.Usuario AS Usuario " + filtro;
        return super.ObtenerLista(sql, Usuario.class);
    }

    public int ActualizarUsuario(int idUsuario, String usuario, String clave, int idPerfil, String nombrePefil, boolean activo) {
        String sql = String.format("UPDATE Administracion.Usuario SET usuario = '%s', clave = '%s', idPerfil = %d, nombrePerfil = '%s', activo = %b WHERE idUsuario = %d", usuario, clave, idPerfil, nombrePefil, activo, idUsuario);
        return super.Ejecutar(sql);
    }

    public int ConectarUsuario(int idUsuario, String ip) {
        String sql = String.format("UPDATE Administracion.Usuario SET conectado = TRUE ,ip = '%s' WHERE idUsuario = %d", ip, idUsuario);
        return super.Ejecutar(sql);
    }

    public int DesconectarUsuario(int idUsuario) {
        String sql = String.format("UPDATE Administracion.Usuario SET conectado = FALSE WHERE idUsuario = %d", idUsuario);
        return super.Ejecutar(sql);
    }

    public int EliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Administracion.Usuario WHERE idUsuario = " + idUsuario;
        return super.Ejecutar(sql);
    }
}
