package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PlantillaSolicitudCotizacionDAO extends BaseDAO {

    public List<Object[]> ObtenerPlantillasSolicitudCotizacion(String filtro) {
        String sql = "SELECT \n"
                + "PlantillaSolicitudCotizacion.idPlantillaSolicitudCotizacion, PlantillaSolicitudCotizacion.nombre, PlantillaSolicitudCotizacion.lineaProduccion, PlantillaSolicitudCotizacion.activo \n"
                + "FROM \n"
                + "Ventas.PlantillaSolicitudCotizacion " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion, String nombre, String lineaProduccion, boolean activo) {
        String sql = String.format("UPDATE Ventas.PlantillaSolicitudCotizacion SET nombre = '%s', lineaProduccion = '%s', activo = %b WHERE idPlantillaSolicitudCotizacion = %d", nombre, lineaProduccion, activo, idPlantillaSolicitudCotizacion);
        return super.Ejecutar(sql);
    }

    public int EliminarPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.PlantillaSolicitudCotizacion WHERE idPlantillaSolicitudCotizacion = " + idPlantillaSolicitudCotizacion;
        return super.Ejecutar(sql);
    }
}
