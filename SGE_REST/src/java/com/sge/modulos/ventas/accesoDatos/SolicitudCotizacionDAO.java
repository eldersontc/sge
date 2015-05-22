package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.SolicitudCotizacion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SolicitudCotizacionDAO extends BaseDAO {

    public List<SolicitudCotizacion> ObtenerSolicitudesCotizacion(String filtro) {
        String sql = "SELECT \n"
                + "SolicitudCotizacion.* \n"
                + "FROM \n"
                + "Ventas.SolicitudCotizacion " + filtro;
        return super.ObtenerLista(sql, SolicitudCotizacion.class);
    }

    public int ActualizarSolicitudCotizacion(SolicitudCotizacion solicitud) {
        String sql = String.format("UPDATE Ventas.SolicitudCotizacion SET descripcion = '%s', idCliente = %d, razonSocialCliente = '%s', "
                + "idListaPrecioProducto = %d, nombreListaPrecioProducto = '%s', idListaPrecioServicio = %d, nombreListaPrecioServicio = '%s', idListaPrecioMaquina = %d, nombreListaPrecioMaquina = '%s', "
                + "idMoneda = %d, simboloMoneda = '%s', idVendedor = %d, nombreVendedor = '%s', idFormaPago = %d, descripcionFormaPago = '%s', "
                + "lineaProduccion = '%s', idContactoCliente = %d, nombreContactoCliente = '%s', cantidad = %d, observacion = '%s' WHERE idSolicitudCotizacion = %d",
                solicitud.getDescripcion(), solicitud.getIdCliente(), solicitud.getRazonSocialCliente(), solicitud.getIdListaPrecioProducto(),
                solicitud.getNombreListaPrecioProducto(), solicitud.getIdListaPrecioServicio(), solicitud.getNombreListaPrecioServicio(),
                solicitud.getIdListaPrecioMaquina(), solicitud.getNombreListaPrecioMaquina(), solicitud.getIdMoneda(), solicitud.getSimboloMoneda(),
                solicitud.getIdVendedor(), solicitud.getNombreVendedor(), solicitud.getIdFormaPago(), solicitud.getDescripcionFormaPago(),
                solicitud.getLineaProduccion(), solicitud.getIdContactoCliente(), solicitud.getNombreContactoCliente(), solicitud.getCantidad(), solicitud.getObservacion(), solicitud.getIdSolicitudCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarSolicitudCotizacion(int idSolicitudCotizacion) {
        String sql = "DELETE FROM Ventas.SolicitudCotizacion WHERE idSolicitudCotizacion = " + idSolicitudCotizacion;
        return super.Ejecutar(sql);
    }

    public int ActualizarEstadoSolicitudCotizacion(int idSolicitudCotizacion, String estado) {
        String sql = String.format("UPDATE Ventas.SolicitudCotizacion SET estado = '%s' WHERE idSolicitudCotizacion = %d", estado, idSolicitudCotizacion);
        return super.Ejecutar(sql);
    }
}
