package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Numeracion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class NumeracionDAO extends BaseDAO {

    public List<Numeracion> ObtenerNumeraciones(String filtro) {
        String sql = "SELECT \n"
                + "Numeracion.* \n"
                + "FROM \n"
                + "Administracion.Numeracion AS Numeracion " + filtro;
        return super.ObtenerLista(sql, Numeracion.class);
    }

    public int ActualizarNumeracion(int idNumeracion, String descripcion, int idEntidad, String nombreEntidad, boolean manual, String serie, int numeroActual, int longitudNumero, boolean tieneImpuesto, double porcentajeImpuesto, boolean activo) {
        String sql = String.format("UPDATE Administracion.Numeracion SET descripcion = '%s', idEntidad = %d, nombreEntidad = '%s', manual = %b, serie = '%s', numeroActual = %s, longitudNumero = %s, tieneImpuesto = %b, porcentajeImpuesto = %s, activo = %b WHERE idNumeracion = %d", descripcion, idEntidad, nombreEntidad, manual, serie, numeroActual, longitudNumero, tieneImpuesto, porcentajeImpuesto, activo, idNumeracion);
        return super.Ejecutar(sql);
    }

    public int EliminarNumeracion(int idNumeracion) {
        String sql = "DELETE FROM Administracion.Numeracion WHERE idNumeracion = " + idNumeracion;
        return super.Ejecutar(sql);
    }
}
