package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Empleado;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EmpleadoDAO extends BaseDAO {

    public List<Empleado> ObtenerEmpleados(String filtro) {
        String sql = "SELECT \n"
                + "Empleado.* \n"
                + "FROM \n"
                + "Administracion.Empleado AS Empleado " + filtro;
        return super.ObtenerLista(sql, Empleado.class);
    }

    public int ActualizarEmpleado(int idEmpleado, String codigo, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoDocumentoIdentidad, String documentoIdentidad, boolean activo) {
        String sql = String.format("UPDATE Administracion.Empleado SET codigo = '%s', nombre = '%s', apellidoPaterno = '%s', apellidoMaterno = '%s', tipoDocumentoIdentidad = '%s', documentoIdentidad = '%s', activo = %b WHERE idEmpleado = %d", codigo, nombre, apellidoPaterno, apellidoMaterno, tipoDocumentoIdentidad, documentoIdentidad, activo, idEmpleado);
        return super.Ejecutar(sql);
    }

    public int EliminarEmpleado(int idEmpleado) {
        String sql = "DELETE FROM Administracion.Empleado WHERE idEmpleado = " + idEmpleado;
        return super.Ejecutar(sql);
    }
}
