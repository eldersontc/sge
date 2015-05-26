package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class PerfilMenuDAO extends BaseDAO {
    
    public int EliminarPerfilMenu(int idPerfil, int idMenu) {
        String sql = String.format("DELETE FROM Administracion.PerfilMenu WHERE idPerfil = %d AND idMenu = %d", idPerfil, idMenu);
        return super.Ejecutar(sql);
    }
}
