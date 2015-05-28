package com.sge.modulos.produccion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.produccion.entidades.ItemOrdenTrabajo;

/**
 *
 * @author elderson
 */
public class ItemOrdenTrabajoDAO extends BaseDAO {

    public int ActualizarItemOrdenTrabajo(ItemOrdenTrabajo item) {
        String sql = String.format("UPDATE Produccion.ItemOrdenTrabajo SET nombre = '%s', idServicioImpresion = %d, "
                + "nombreServicioImpresion = '%s', impresionVinil = %b, impresionBanner = %b, idMaquina = %d, "
                + "descripcionMaquina = '%s', largoMaximoPliegoMaquina = %d, altoMaximoPliegoMaquina = %d, idMaterial = %d, codigoMaterial = '%s', nombreMaterial = '%s', largoMaterial = %s, "
                + "altoMaterial %s, idUnidadMaterial = %d, abreviacionUnidadMaterial = '%s', factorUnidadMaterial = %d, nombreTipoUnidad = '%s', "
                + "unidadMedidaAbierta = '%s', medidaAbierta = %b, medidaCerrada = %b, tiraRetira = %b, grafico = %b, "
                + "material = %b, servicioImpresion = %b, fondo = %b, tipoUnidad = %b, largoMedidaAbierta = %s, "
                + "altoMedidaAbierta = %s, largoMedidaCerrada = %s, altoMedidaCerrada = %s, tiraColor = %s, retiraColor = %s, "
                + "dFondo = %s, cantidadTipoUnidad = %d, largoFormatoImpresion = %s, altoFormatoImpresion = %s, separacionX = %s, "
                + "separacionY = %s, cantidadPliegos = %d, ubicacionGraficoPrecorte = '%s', ubicacionGraficoImpresion = '%s', "
                + "graficoPrecorteGirado = %b, graficoImpresionGirado = %b, cantidadPiezasPrecorte = %d, cantidadPiezasImpresion = %d, "
                + "idMetodoImpresion = %d, descripcionMetodoImpresion = '%s', cantidadPases = %d, cantidadCambios = %d, "
                + "factorHorizontal = %d, factorVertical = %d, letras = '%s', cantidadMaterial = %d, cantidadDemasia = %d, "
                + "cantidadDemasiaMaterial = %d, cantidadProduccion = %d, cantidad = %d, cantidadPaginasSobrantes = %d, observacion = '%s',  "
                + "precioMaquina = %s, precioMaterial = %s, totalMaquina = %s, totalMaterial = %s, total = %s "
                + "WHERE idItemOrdenTrabajo = %d",
                item.getNombre(), item.getIdServicioImpresion(), item.getNombreServicioImpresion(), item.isImpresionVinil(),
                item.isImpresionBanner(), item.getIdMaquina(), item.getDescripcionMaquina(), item.getLargoMaximoPliegoMaquina(), item.getAltoMaximoPliegoMaquina(), item.getIdMaterial(),
                item.getCodigoMaterial(), item.getNombreMaterial(), item.getAltoMaterial(), item.getLargoMaterial(), item.getIdUnidadMaterial(),
                item.getAbreviacionUnidadMaterial(), item.getFactorUnidadMaterial(), item.getNombreTipoUnidad(), item.getUnidadMedidaAbierta(), item.isMedidaAbierta(),
                item.isMedidaCerrada(), item.isTiraRetira(), item.isGrafico(), item.isMaterial(), item.isServicioImpresion(),
                item.isFondo(), item.isTipoUnidad(), item.getLargoMedidaAbierta(), item.getAltoMedidaAbierta(),
                item.getLargoMedidaCerrada(), item.getAltoMedidaCerrada(), item.getTiraColor(), item.getRetiraColor(),
                item.getdFondo(), item.getCantidadTipoUnidad(), item.getLargoFormatoImpresion(), item.getAltoFormatoImpresion(),
                item.getSeparacionX(), item.getSeparacionY(), item.getCantidadPliegos(), item.getUbicacionGraficoPrecorte(),
                item.getUbicacionGraficoImpresion(), item.isGraficoPrecorteGirado(), item.isGraficoImpresionGirado(), item.getCantidadPiezasPrecorte(),
                item.getCantidadPiezasImpresion(), item.getIdMetodoImpresion(), item.getDescripcionMetodoImpresion(), item.getCantidadPases(),
                item.getCantidadCambios(), item.getFactorHorizontal(), item.getFactorVertical(), item.getLetras(), item.getCantidadMaterial(),
                item.getCantidadDemasia(), item.getCantidadDemasiaMaterial(), item.getCantidadProduccion(), item.getCantidad(),
                item.getCantidadPaginasSobrantes(), item.getObservacion(), item.getPrecioMaquina(), item.getPrecioMaterial(), item.getTotalMaquina(), item.getTotalMaterial(),
                item.getTotal(), item.getIdItemOrdenTrabajo());
        return super.Ejecutar(sql);
    }

    public int EliminarItemOrdenTrabajo(int idItemOrdenTrabajo) {
        String sql = "DELETE FROM Produccion.ItemOrdenTrabajo WHERE idItemOrdenTrabajo = " + idItemOrdenTrabajo;
        return super.Ejecutar(sql);
    }

    public int EliminarItemOrdenTrabajoPorIdOrdenTrabajo(int idOrdenTrabajo) {
        String sql = "DELETE FROM Produccion.ItemOrdenTrabajo WHERE idOrdenTrabajo = " + idOrdenTrabajo;
        return super.Ejecutar(sql);
    }
}
