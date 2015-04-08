package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class ReporteDAO extends AdministracionDAO {

    public List<Object[]> ObtenerReportes(String filtro) {
        String sql = "SELECT \n"
                + "Reporte.idReporte, Reporte.nombre, Reporte.entidad, Reporte.activo \n"
                + "FROM \n"
                + "Administracion.Reporte \n" + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarReporte(int idReporte, String nombre, String entidad, String ubicacion, boolean activo) {
        String sql = String.format("UPDATE Administracion.Reporte SET nombre = '%s', entidad = '%s', ubicacion = '%s', activo = %b WHERE idReporte = %d", nombre, entidad, ubicacion, activo, idReporte);
        return super.Ejecutar(sql);
    }
    
    public int EliminarReporte(int idReporte) {
        String sql = "DELETE FROM Administracion.Reporte WHERE idReporte = " + idReporte;
        return super.Ejecutar(sql);
    }
}
