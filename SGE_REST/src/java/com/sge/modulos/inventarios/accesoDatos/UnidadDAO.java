package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.inventarios.entidades.Unidad;
import java.util.List;

/**
 *
 * @author elderson
 */
public class UnidadDAO extends BaseDAO {

    public List<Unidad> ObtenerUnidades(String filtro) {
        String sql = "SELECT \n"
                + "Unidad.* \n"
                + "FROM \n"
                + "Inventarios.Unidad AS Unidad " + filtro;
        return super.ObtenerLista(sql, Unidad.class);
    }

    public int ActualizarUnidad(int idUnidad, String abreviacion, String descripcion, boolean activo) {
        String sql = String.format("UPDATE Inventarios.Unidad SET abreviacion = '%s', descripcion = '%s', activo = %b WHERE idUnidad = %d", abreviacion, descripcion, activo, idUnidad);
        return super.Ejecutar(sql);
    }
    
    public int EliminarUnidad(int idUnidad) {
        String sql = "DELETE FROM Inventarios.Unidad WHERE idUnidad = " + idUnidad;
        return super.Ejecutar(sql);
    }
}
