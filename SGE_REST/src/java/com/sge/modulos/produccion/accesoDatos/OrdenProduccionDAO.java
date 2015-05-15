package com.sge.modulos.produccion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class OrdenProduccionDAO extends BaseDAO {
    
    public List<Object[]> ObtenerOrdenesProduccion(String filtro) {
        String sql = "SELECT \n"
                + "OrdenProduccion.idOrdenProduccion, OrdenProduccion.numero, OrdenProduccion.fechaCreacion, OrdenProduccion.razonSocialCliente, OrdenProduccion.nombreResponsable \n"
                + "FROM \n"
                + "Produccion.OrdenProduccion " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarOrdenProduccion(int idOrdenProduccion, int idCliente, String razonSocialCliente, int idResponsable, String nombreResponsable) {
        String sql = String.format("UPDATE Produccion.OrdenProduccion SET idCliente = %d, razonSocialCliente = '%s',idResponsable = %d, nombreResponsable = '%s' WHERE idOrdenProduccion = %d", idCliente, razonSocialCliente, idResponsable, nombreResponsable, idOrdenProduccion);
        return super.Ejecutar(sql);
    }

    public int EliminarOrdenProduccion(int idOrdenProduccion) {
        String sql = "DELETE FROM Produccion.OrdenProduccion WHERE idOrdenProduccion = " + idOrdenProduccion;
        return super.Ejecutar(sql);
    }
}
