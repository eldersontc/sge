package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ItemPlantillaSolicitudCotizacion;

/**
 *
 * @author elderson
 */
public class ItemPlantillaSolicitudCotizacionDAO extends BaseDAO {

    public int ActualizarItemPlantillaSolicitudCotizacion(ItemPlantillaSolicitudCotizacion item) {
        String sql = String.format("UPDATE Ventas.ItemPlantillaSolicitudCotizacion SET nombre = '%s', idServicioImpresion = %d, "
                + "nombreServicioImpresion = '%s', idMaterial = %d, codigoMaterial = '%s', nombreMaterial = '%s', altoMaterial = %s, largoMaterial = %s, idUnidadMaterial = %d, abreviacionUnidadMaterial = '%s', factorUnidadMaterial = %d, nombreTipoUnidad = '%s', "
                + "unidadMedidaAbierta = '%s', medidaAbierta = %b, medidaCerrada = %b, tiraRetira = %b, grafico = %b, "
                + "material = %b, servicioImpresion = %b, fondo = %b, tipoUnidad = %b WHERE idItemPlantillaSolicitudCotizacion = %d",
                item.getNombre(), item.getIdServicioImpresion(), item.getNombreServicioImpresion(), item.getIdMaterial(),
                item.getCodigoMaterial(), item.getNombreMaterial(), item.getAltoMaterial(), item.getLargoMaterial(), item.getIdUnidadMaterial(), item.getAbreviacionUnidadMaterial(), item.getFactorUnidadMaterial(), item.getNombreTipoUnidad(), item.getUnidadMedidaAbierta(), item.isMedidaAbierta(),
                item.isMedidaCerrada(), item.isTiraRetira(), item.isGrafico(), item.isMaterial(), item.isServicioImpresion(),
                item.isFondo(), item.isTipoUnidad(), item.getIdItemPlantillaSolicitudCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarItemPlantillaSolicitudCotizacion(int idItemPlantillaSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.ItemPlantillaSolicitudCotizacion WHERE idItemPlantillaSolicitudCotizacion = " + idItemPlantillaSolicitudCotizacion;
        return super.Ejecutar(sql);
    }

    public int EliminarItemPlantillaSolicitudCotizacionPorIdPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.ItemPlantillaSolicitudCotizacion WHERE idPlantillaSolicitudCotizacion = " + idPlantillaSolicitudCotizacion;
        return super.Ejecutar(sql);
    }
}
