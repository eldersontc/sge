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

    public int ActualizarUsuario(int idUsuario, String usuario, String clave, boolean activo) {
        String sql = String.format("UPDATE Administracion.Usuario SET usuario = '%s', clave = '%s', activo = %b WHERE idUsuario = %d", usuario, clave, activo, idUsuario);
        return super.Ejecutar(sql);
    }

    public int EliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Administracion.Usuario WHERE idUsuario = " + idUsuario;
        return super.Ejecutar(sql);
    }
}
