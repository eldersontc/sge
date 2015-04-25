package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.SolicitudCotizacion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SolicitudCotizacionDAO extends BaseDAO {

    public List<Object[]> ObtenerSolicitudesCotizacion(String filtro) {
        String sql = "SELECT \n"
                + "SolicitudCotizacion.idSolicitudCotizacion, SolicitudCotizacion.numero, SolicitudCotizacion.descripcion, SolicitudCotizacion.fechaCreacion, \n"
                + "SolicitudCotizacion.razonSocialCliente, SolicitudCotizacion.nombreVendedor, SolicitudCotizacion.simboloMoneda \n"
                + "FROM \n"
                + "Ventas.SolicitudCotizacion " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarSolicitudCotizacion(SolicitudCotizacion solicitud) {
        String sql = String.format("UPDATE Ventas.SolicitudCotizacion SET descripcion = '%s', idCliente = %d, razonSocialCliente = '%s', "
                + "idMoneda = %d, simboloMoneda = '%s', idVendedor = %d, nombreVendedor = '%s', idFormaPago = %d, descripcionFormaPago = '%s', "
                + "lineaProduccion = '%s', cantidad = %d, observacion = '%s' WHERE idSolicitudCotizacion = %d",
                solicitud.getDescripcion(), solicitud.getIdCliente(), solicitud.getRazonSocialCliente(), solicitud.getIdMoneda(), solicitud.getSimboloMoneda(),
                solicitud.getIdVendedor(), solicitud.getNombreVendedor(), solicitud.getIdFormaPago(), solicitud.getDescripcionFormaPago(),
                solicitud.getLineaProduccion(), solicitud.getCantidad(), solicitud.getObservacion(), solicitud.getIdSolicitudCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarSolicitudCotizacion(int idSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.SolicitudCotizacion WHERE idSolicitudCotizacion = " + idSolicitudCotizacion;
        return super.Ejecutar(sql);
    }
}
