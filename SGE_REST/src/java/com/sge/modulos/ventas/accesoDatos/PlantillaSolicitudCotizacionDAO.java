package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.PlantillaSolicitudCotizacion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PlantillaSolicitudCotizacionDAO extends BaseDAO {

    public List<PlantillaSolicitudCotizacion> ObtenerPlantillasSolicitudCotizacion(String filtro) {
        String sql = "SELECT \n"
                + "PlantillaSolicitudCotizacion.* \n"
                + "FROM \n"
                + "Ventas.PlantillaSolicitudCotizacion AS PlantillaSolicitudCotizacion " + filtro;
        return super.ObtenerLista(sql, PlantillaSolicitudCotizacion.class);
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
