package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.Cotizacion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class CotizacionDAO extends BaseDAO {
    
    public List<Cotizacion> ObtenerCotizaciones(String filtro) {
        String sql = "SELECT \n"
                + "Cotizacion.* \n"
                + "FROM \n"
                + "Ventas.Cotizacion " + filtro;
        return super.ObtenerLista(sql, Cotizacion.class);
    }

    public List<Cotizacion> ObtenerCotizacionesPorPresupuesto(int idPresupuesto) {
        String sql = "SELECT \n" +
                    "  Cotizacion.* \n" +
                    "FROM \n" +
                    "  Ventas.Presupuesto AS Presupuesto \n" +
                    "  INNER JOIN Ventas.ItemPresupuesto \n" +
                    "  ON Presupuesto.idPresupuesto = ItemPresupuesto.idPresupuesto \n" +
                    "  INNER JOIN Ventas.Cotizacion \n" +
                    "  ON ItemPresupuesto.idCotizacion = Cotizacion.idCotizacion\n" +
                    "WHERE \n" +
                    "  Presupuesto.idPresupuesto = " + idPresupuesto;
        return super.ObtenerLista(sql, Cotizacion.class);
    }
    
    public int ActualizarCotizacion(Cotizacion cotizacion) {
        String sql = String.format("UPDATE Ventas.Cotizacion SET descripcion = '%s', idCliente = %d, razonSocialCliente = '%s', "
                + "idListaPrecioProducto = %d, nombreListaPrecioProducto = '%s', idListaPrecioServicio = %d, nombreListaPrecioServicio = '%s', idListaPrecioMaquina = %d, nombreListaPrecioMaquina = '%s', "
                + "idMoneda = %d, simboloMoneda = '%s', idCotizador = %d, nombreCotizador = '%s',idVendedor = %d, nombreVendedor = '%s', idFormaPago = %d, descripcionFormaPago = '%s', "
                + "lineaProduccion = '%s', idContactoCliente = %d, nombreContactoCliente = '%s', cantidad = %d, observacion = '%s' WHERE idCotizacion = %d",
                cotizacion.getDescripcion(), cotizacion.getIdCliente(), cotizacion.getRazonSocialCliente(), cotizacion.getIdListaPrecioProducto(), cotizacion.getNombreListaPrecioProducto(), 
                cotizacion.getIdListaPrecioServicio(), cotizacion.getNombreListaPrecioServicio(), cotizacion.getIdListaPrecioMaquina(), cotizacion.getNombreListaPrecioMaquina(), cotizacion.getIdMoneda(), 
                cotizacion.getSimboloMoneda(),
                cotizacion.getIdCotizador(), cotizacion.getNombreCotizador(), cotizacion.getIdVendedor(), cotizacion.getNombreVendedor(), cotizacion.getIdFormaPago(), cotizacion.getDescripcionFormaPago(),
                cotizacion.getLineaProduccion(), cotizacion.getIdContactoCliente(), cotizacion.getNombreContactoCliente(), cotizacion.getCantidad(), cotizacion.getObservacion(), cotizacion.getIdCotizacion());
        return super.Ejecutar(sql);
    }

    public int EliminarCotizacion(int idCotizacion) {
        String sql = "DELETE FROM Ventas.Cotizacion WHERE idCotizacion = " + idCotizacion;
        return super.Ejecutar(sql);
    }
    
    public int ActualizarEstadoCotizacion(int idCotizacion, String estado) {
        String sql = String.format("UPDATE Ventas.Cotizacion SET estado = '%s' WHERE idCotizacion = %d", estado, idCotizacion);
        return super.Ejecutar(sql);
    }
    
    public int ActualizarIdYNumeroPresupuesto(int idCotizacion, int idPresupuesto, String numeroPresupuesto) {
        String sql = String.format("UPDATE Ventas.Cotizacion SET idPresupuesto = %d, numeroPresupuesto = '%s' WHERE idCotizacion = %d", idPresupuesto, numeroPresupuesto, idCotizacion);
        return super.Ejecutar(sql);
    }
}
