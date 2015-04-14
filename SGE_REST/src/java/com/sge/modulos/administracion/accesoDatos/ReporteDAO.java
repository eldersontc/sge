package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class ReporteDAO extends AdministracionDAO {

    public List<Object[]> ObtenerReportes(String filtro) {
        String sql = "SELECT \n"
                + "Reporte.idReporte, Reporte.nombre, Entidad.nombre AS entidad, Reporte.activo \n"
                + "FROM \n"
                + "Administracion.Reporte AS Reporte \n"
                + "INNER JOIN Administracion.Entidad AS Entidad ON Reporte.idEntidad = Entidad.idEntidad " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarReporte(int idReporte, String nombre, int idEntidad, String ubicacion, boolean activo) {
        String sql = String.format("UPDATE Administracion.Reporte SET nombre = '%s', idEntidad = %d, ubicacion = '%s', activo = %b WHERE idReporte = %d", nombre, idEntidad, ubicacion, activo, idReporte);
        return super.Ejecutar(sql);
    }
    
    public int EliminarReporte(int idReporte) {
        String sql = "DELETE FROM Administracion.Reporte WHERE idReporte = " + idReporte;
        return super.Ejecutar(sql);
    }
}
