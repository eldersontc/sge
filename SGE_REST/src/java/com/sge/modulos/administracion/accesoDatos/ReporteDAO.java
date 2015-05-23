package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Reporte;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ReporteDAO extends BaseDAO {

    public List<Reporte> ObtenerReportes(String filtro) {
        String sql = "SELECT \n"
                + "Reporte.* \n"
                + "FROM \n"
                + "Administracion.Reporte AS Reporte " + filtro;
        return super.ObtenerLista(sql, Reporte.class);
    }

    public int ActualizarReporte(int idReporte, String nombre, int idEntidad, String nombreEntidad, String ubicacion, boolean activo) {
        String sql = String.format("UPDATE Administracion.Reporte SET nombre = '%s', idEntidad = %d, nombreEntidad = '%s', ubicacion = '%s', activo = %b WHERE idReporte = %d", nombre, idEntidad, nombreEntidad, ubicacion, activo, idReporte);
        return super.Ejecutar(sql);
    }

    public int EliminarReporte(int idReporte) {
        String sql = "DELETE FROM Administracion.Reporte WHERE idReporte = " + idReporte;
        return super.Ejecutar(sql);
    }
}
