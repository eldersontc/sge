package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ServicioDAO extends BaseDAO {
    
    public List<Object[]> ObtenerServicios(String filtro) {
        String sql = "SELECT \n"
                + "Servicio.idServicio, Servicio.codigo, Servicio.descripcion, Servicio.activo \n"
                + "FROM \n"
                + "Ventas.Servicio " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarServicio(int idServicio, String codigo, String descripcipon, boolean servicioImpresion, boolean activo) {
        String sql = String.format("UPDATE Ventas.Servicio SET codigo = '%s', descripcion = '%s', servicioImpresion = %b, activo = %b WHERE idServicio = %d", codigo, descripcipon, servicioImpresion, activo, idServicio);
        return super.Ejecutar(sql);
    }

    public int EliminarServicio(int idServicio) {
        String sql = "DELETE FROM Ventas.Servicio WHERE idServicio = " + idServicio;
        return super.Ejecutar(sql);
    }
}
