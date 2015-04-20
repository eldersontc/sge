package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class NumeracionDAO extends BaseDAO {

    public List<Object[]> ObtenerNumeraciones(String filtro) {
        String sql = "SELECT \n"
                + "Numeracion.idNumeracion, Numeracion.descripcion, Entidad.nombre as entidad, Numeracion.activo \n"
                + "FROM \n"
                + "Administracion.Numeracion AS Numeracion \n"
                + "INNER JOIN Administracion.Entidad AS Entidad ON Numeracion.idEntidad = Entidad.idEntidad " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarNumeracion(int idNumeracion, String descripcion, int idEntidad, boolean manual, String serie, int numeroActual, int longitudNumero, boolean tieneImpuesto, double porcentajeImpuesto, boolean activo) {
        String sql = String.format("UPDATE Administracion.Numeracion SET descripcion = '%s', idEntidad = %d, manual = %b, serie = '%s', numeroActual = %d, longitudNumero = %d, tieneImpuesto = %b, porcentajeImpuesto = %d, activo = %b WHERE idNumeracion = %d", descripcion, idEntidad, manual, serie, numeroActual, longitudNumero, tieneImpuesto, porcentajeImpuesto, activo, idNumeracion);
        return super.Ejecutar(sql);
    }

    public int EliminarNumeracion(int idNumeracion) {
        String sql = "DELETE FROM Administracion.Numeracion WHERE idNumeracion = " + idNumeracion;
        return super.Ejecutar(sql);
    }
}
