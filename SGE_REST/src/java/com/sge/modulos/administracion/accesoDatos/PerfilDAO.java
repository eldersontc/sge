package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Perfil;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PerfilDAO extends BaseDAO {
    
    public List<Perfil> ObtenerPerfiles(String filtro) {
        String sql = "SELECT \n"
                + "Perfil.* \n"
                + "FROM \n"
                + "Administracion.Perfil AS Perfil " + filtro;
        return super.ObtenerLista(sql, Perfil.class);
    }

    public int ActualizarPerfil(int idPerfil, String nombre, boolean activo) {
        String sql = String.format("UPDATE Administracion.Perfil SET nombre = '%s', activo = %b WHERE idPerfil = %d", nombre, activo, idPerfil);
        return super.Ejecutar(sql);
    }

    public int EliminarPerfil(int idPerfil) {
        String sql = "DELETE FROM Administracion.Perfil WHERE idPerfil = " + idPerfil;
        return super.Ejecutar(sql);
    }
}
