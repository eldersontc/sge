package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.Servicio;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ServicioDAO extends BaseDAO {

    public List<Servicio> ObtenerServicios(String filtro) {
        String sql = "SELECT \n"
                + "Servicio.* \n"
                + "FROM \n"
                + "Ventas.Servicio AS Servicio " + filtro;
        return super.ObtenerLista(sql, Servicio.class);
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
