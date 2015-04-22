package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ServicioUnidadDAO extends BaseDAO {

    public int ActualizarServicioUnidad(int idServicioUnidad, int factor) {
        String sql = String.format("UPDATE Ventas.ServicioUnidad SET factor = %d WHERE idServicioUnidad = %d", factor, idServicioUnidad);
        return super.Ejecutar(sql);
    }

    public int EliminarServicioUnidad(int idServicioUnidad) {
        String sql = "DELETE FROM Ventas.ServicioUnidad WHERE idServicioUnidad = " + idServicioUnidad;
        return super.Ejecutar(sql);
    }
    
    public int EliminarServicioUnidadPorIdServicio(int idServicio) {
        String sql = "DELETE FROM Ventas.ServicioUnidad WHERE idServicio = " + idServicio;
        return super.Ejecutar(sql);
    }
}
