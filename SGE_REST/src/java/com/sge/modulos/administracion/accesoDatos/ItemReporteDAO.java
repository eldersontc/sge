package com.sge.modulos.administracion.accesoDatos;

/**
 *
 * @author elderson
 */
public class ItemReporteDAO extends AdministracionDAO {

    public int ActualizarItemReporte(int idItemReporte, String nombre, boolean asignarId, String valor) {
        String sql = String.format("UPDATE Administracion.ItemReporte SET nombre = '%s', asignarId = '%b', valor = '%s' WHERE idItemReporte = %d", nombre, asignarId, valor, idItemReporte);
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemReporte(int idItemReporte) {
        String sql = "DELETE FROM Administracion.ItemReporte WHERE idItemReporte = " + idItemReporte;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemReportePorIdReporte(int idReporte) {
        String sql = "DELETE FROM Administracion.ItemReporte WHERE idReporte = " + idReporte;
        return super.Ejecutar(sql);
    }
}
