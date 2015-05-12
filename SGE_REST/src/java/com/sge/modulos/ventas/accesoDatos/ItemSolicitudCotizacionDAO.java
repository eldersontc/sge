package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ItemSolicitudCotizacion;

/**
 *
 * @author elderson
 */
public class ItemSolicitudCotizacionDAO extends BaseDAO {

    public int ActualizarItemSolicitudCotizacion(ItemSolicitudCotizacion item) {
        String sql = String.format("UPDATE Ventas.ItemSolicitudCotizacion SET nombre = '%s', idServicioImpresion = %d, "
                + "nombreServicioImpresion = '%s', idMaquina = %d, descripcionMaquina = '%s', largoMaximoPliegoMaquina = %d, "
                + "altoMaximoPliegoMaquina = %d, idMaterial = %d, nombreMaterial = '%s', largoMaterial = %s, altoMaterial = %s, nombreTipoUnidad = '%s', "
                + "unidadMedidaAbierta = '%s', medidaAbierta = %b, medidaCerrada = %b, tiraRetira = %b, grafico = %b, "
                + "material = %b, servicioImpresion = %b, fondo = %b, tipoUnidad = %b, largoMedidaAbierta = %s, "
                + "altoMedidaAbierta = %s, largoMedidaCerrada = %s, altoMedidaCerrada = %s, tiraColor = %s, retiraColor = %s, "
                + "dFondo = %s, cantidadTipoUnidad = %d, acabados = '%s' WHERE idItemSolicitudCotizacion = %d",
                item.getNombre(), item.getIdServicioImpresion(), item.getNombreServicioImpresion(), item.getIdMaquina(), item.getDescripcionMaquina(), item.getLargoMaximoPliegoMaquina(), item.getAltoMaximoPliegoMaquina(), item.getIdMaterial(),
                item.getNombreMaterial(), item.getLargoMaterial(), item.getAltoMaterial(), item.getNombreTipoUnidad(), item.getUnidadMedidaAbierta(), item.isMedidaAbierta(),
                item.isMedidaCerrada(), item.isTiraRetira(), item.isGrafico(), item.isMaterial(), item.isServicioImpresion(),
                item.isFondo(), item.isTipoUnidad(), item.getLargoMedidaAbierta(), item.getAltoMedidaAbierta(),
                item.getLargoMedidaCerrada(), item.getAltoMedidaCerrada(), item.getTiraColor(), item.getRetiraColor(),
                item.getdFondo(), item.getCantidadTipoUnidad(), item.getAcabados(), item.getIdItemSolicitudCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarItemSolicitudCotizacion(int idItemSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.ItemSolicitudCotizacion WHERE idItemSolicitudCotizacion = " + idItemSolicitudCotizacion;
        return super.Ejecutar(sql);
    }

    public int EliminarItemSolicitudCotizacionPorIdSolicitudCotizacion(int idSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.ItemSolicitudCotizacion WHERE idSolicitudCotizacion = " + idSolicitudCotizacion;
        return super.Ejecutar(sql);
    }
}
