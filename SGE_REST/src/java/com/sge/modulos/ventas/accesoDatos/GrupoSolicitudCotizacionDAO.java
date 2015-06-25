package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.GrupoSolicitudCotizacion;

/**
 *
 * @author elderson
 */
public class GrupoSolicitudCotizacionDAO extends BaseDAO {
    
    public int ActualizarGrupoSolicitudCotizacion(GrupoSolicitudCotizacion grupo) {
        String sql = String.format("UPDATE Ventas.GrupoSolicitudCotizacion SET nombre = '%s', lineaProduccion = '%s', cantidad = %s "
                + "WHERE idGrupoSolicitudCotizacion = %d",
                grupo.getNombre(), grupo.getLineaProduccion(), grupo.getCantidad(), grupo.getIdGrupoSolicitudCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarGrupoSolicitudCotizacion(int idGrupoSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.GrupoSolicitudCotizacion WHERE idGrupoSolicitudCotizacion = " + idGrupoSolicitudCotizacion;
        return super.Ejecutar(sql);
    }
    
    public int EliminarGrupoSolicitudCotizacionPorIdSolicitudCotizacion(int idSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.GrupoSolicitudCotizacion WHERE idSolicitudCotizacion = " + idSolicitudCotizacion;
        return super.Ejecutar(sql);
    }
}
