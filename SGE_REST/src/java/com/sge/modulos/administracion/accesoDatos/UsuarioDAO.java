package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class UsuarioDAO extends AdministracionDAO {

    public List<Object[]> ObtenerUsuarios() {
        String sql = "SELECT \n"
                + "Usuario.idUsuario, Usuario.usuario, Usuario.clave, Usuario.activo \n"
                + "FROM \n"
                + "Administracion.Usuario \n";
        return super.ObtenerLista(sql);
    }

    public int ActualizarUsuario(int idUsuario, String usuario, String clave, boolean activo) {
        String sql = "UPDATE Administracion.Usuario SET usuario = '" + usuario + "', clave = '" + clave + "', activo = " + activo + " WHERE idUsuario = " + idUsuario;
        return super.Ejecutar(sql);
    }
    
    public int EliminarUsuario(int idUsuario) {
        String sql = "DELETE Administracion.Usuario WHERE idUsuario = " + idUsuario;
        return super.Ejecutar(sql);
    }
}
