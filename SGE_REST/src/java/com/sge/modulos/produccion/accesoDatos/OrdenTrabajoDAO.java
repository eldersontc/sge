package com.sge.modulos.produccion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.produccion.entidades.OrdenTrabajo;
import java.util.List;

/**
 *
 * @author elderson
 */
public class OrdenTrabajoDAO extends BaseDAO {
    
    public List<OrdenTrabajo> ObtenerOrdenesTrabajo(String filtro) {
        String sql = "SELECT \n"
                + "OrdenTrabajo.* \n"
                + "FROM \n"
                + "Produccion.OrdenTrabajo " + filtro;
        return super.ObtenerLista(sql, OrdenTrabajo.class);
    }

    public int ActualizarOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        String sql = String.format("UPDATE Produccion.OrdenTrabajo SET descripcion = '%s', idCliente = %d, razonSocialCliente = '%s', "
                + "idListaPrecioProducto = %d, nombreListaPrecioProducto = '%s', idListaPrecioServicio = %d, nombreListaPrecioServicio = '%s', idListaPrecioMaquina = %d, nombreListaPrecioMaquina = '%s', "
                + "idMoneda = %d, simboloMoneda = '%s', idCotizador = %d, nombreCotizador = '%s',idVendedor = %d, nombreVendedor = '%s', idResponsable = %d, nombreResponsable = '%s', "
                + "lineaProduccion = '%s', fechaTentativaEntrega = %s, prioridad = '%s', cantidad = %d, observacion = '%s' WHERE idOrdenTrabajo = %d",
                ordenTrabajo.getDescripcion(), ordenTrabajo.getIdCliente(), ordenTrabajo.getRazonSocialCliente(), ordenTrabajo.getIdListaPrecioProducto(), ordenTrabajo.getNombreListaPrecioProducto(), 
                ordenTrabajo.getIdListaPrecioServicio(), ordenTrabajo.getNombreListaPrecioServicio(), ordenTrabajo.getIdListaPrecioMaquina(), ordenTrabajo.getNombreListaPrecioMaquina(), ordenTrabajo.getIdMoneda(), 
                ordenTrabajo.getSimboloMoneda(),
                ordenTrabajo.getIdCotizador(), ordenTrabajo.getNombreCotizador(), ordenTrabajo.getIdVendedor(), ordenTrabajo.getNombreVendedor(), ordenTrabajo.getIdResponsable(), ordenTrabajo.getNombreResponsable(),
                ordenTrabajo.getLineaProduccion(), ordenTrabajo.getFechaTentativaEntrega(), ordenTrabajo.getPrioridad(), ordenTrabajo.getCantidad(), ordenTrabajo.getObservacion(), ordenTrabajo.getIdOrdenTrabajo());
        return super.Ejecutar(sql);
    }

    public int EliminarOrdenTrabajo(int idOrdenTrabajo) {
        String sql = "DELETE FROM Produccion.OrdenTrabajo WHERE idOrdenTrabajo = " + idOrdenTrabajo;
        return super.Ejecutar(sql);
    }
}
