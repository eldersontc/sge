package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ServicioCotizacionDAO extends BaseDAO {
    
    public int ActualizarServicioCotizacion(int idServicioCotizacion, boolean precioManual, double precio, double cantidad, double total) {
        String sql = String.format("UPDATE Ventas.ServicioCotizacion SET precioManual = %b, precio = %s, cantidad = %s, total = %s WHERE idServicioCotizacion = %d", precioManual, precio, cantidad, total, idServicioCotizacion);
        return super.Ejecutar(sql);
    }

    public int EliminarServicioCotizacion(int idServicioCotizacion) {
        String sql = "DELETE FROM Ventas.ServicioCotizacion WHERE idServicioCotizacion = " + idServicioCotizacion;
        return super.Ejecutar(sql);
    }
    
    public int EliminarServicioCotizacionPorIdItemCotizacion(int idItemCotizacion) {
        String sql = "DELETE FROM Ventas.ServicioCotizacion WHERE idItemCotizacion = " + idItemCotizacion;
        return super.Ejecutar(sql);
    }
}
