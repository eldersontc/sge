package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ItemCotizacion;

/**
 *
 * @author elderson
 */
public class ItemCotizacionDAO extends BaseDAO {
    
    public int ActualizarItemCotizacion(ItemCotizacion item) {
        String sql = String.format("UPDATE Ventas.ItemCotizacion SET nombre = '%s', idServicioImpresion = %d, "
                + "nombreServicioImpresion = '%s', idMaquina = %d, descripcionMaquina = '%s', idMaterial = %d, nombreMaterial = '%s', nombreTipoUnidad = '%s', "
                + "unidadMedidaAbierta = '%s', medidaAbierta = %b, medidaCerrada = %b, tiraRetira = %b, grafico = %b, "
                + "material = %b, servicioImpresion = %b, fondo = %b, tipoUnidad = %b, largoMedidaAbierta = %s, "
                + "altoMedidaAbierta = %s, largoMedidaCerrada = %s, altoMedidaCerrada = %s, tiraColor = %s, retiraColor = %s, "
                + "dFondo = %s WHERE idItemCotizacion = %d",
                item.getNombre(), item.getIdServicioImpresion(), item.getNombreServicioImpresion(), item.getIdMaquina(), item.getDescripcionMaquina(), item.getIdMaterial(),
                item.getNombreMaterial(), item.getNombreTipoUnidad(), item.getUnidadMedidaAbierta(), item.isMedidaAbierta(),
                item.isMedidaCerrada(), item.isTiraRetira(), item.isGrafico(), item.isMaterial(), item.isServicioImpresion(),
                item.isFondo(), item.isTipoUnidad(), item.getLargoMedidaAbierta(), item.getAltoMedidaAbierta(),
                item.getLargoMedidaCerrada(), item.getAltoMedidaCerrada(), item.getTiraColor(), item.getRetiraColor(),
                item.getdFondo(), item.getIdItemCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarItemCotizacion(int idItemCotizacion) {
        String sql = "DELETE FROM Ventas.ItemCotizacion WHERE idItemCotizacion = " + idItemCotizacion;
        return super.Ejecutar(sql);
    }

    public int EliminarItemCotizacionPorIdCotizacion(int idCotizacion) {
        String sql = "DELETE FROM Ventas.ItemCotizacion WHERE idCotizacion = " + idCotizacion;
        return super.Ejecutar(sql);
    }
}
