package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Filtro;
import java.util.List;

/**
 *
 * @author elderson
 */
public class FiltroDAO extends BaseDAO {

    public List<Filtro> ObtenerFiltros(String filtro) {
        String sql = "SELECT \n"
                + "Filtro.* \n"
                + "FROM \n"
                + "Administracion.Filtro AS Filtro " + filtro;
        return super.ObtenerLista(sql, Filtro.class);
    }

    public int ActualizarFiltro(Filtro filtro) {
        String sql = String.format("UPDATE Administracion.Filtro SET idEntidad = %d, nombreEntidad = '%s', idUsuario = %d, usuario = '%s', filtro = '%s', activo = %b WHERE idFiltro = %d", filtro.getIdEntidad(), filtro.getNombreEntidad(), filtro.getIdUsuario(), filtro.getUsuario(), filtro.getFiltro(), filtro.isActivo(), filtro.getIdFiltro());
        return super.Ejecutar(sql);
    }

    public int EliminarFiltro(int idFiltro) {
        String sql = "DELETE FROM Administracion.Filtro WHERE idFiltro = " + idFiltro;
        return super.Ejecutar(sql);
    }
}
