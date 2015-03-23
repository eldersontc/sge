package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class EmpleadoDAO extends AdministracionDAO {
    
    public List<Object[]> ObtenerEmpleados() {
        String sql = "SELECT \n"
                + "Empleado.idEmpleado, Empleado.codigo, Empleado.nombre, Empleado.apellidoPaterno, Empleado.apellidoMaterno, Empleado.tipoDocumentoIdentidad, Empleado.documentoIdentidad, Empleado.activo \n"
                + "FROM \n"
                + "Administracion.Empleado \n";
        return super.ObtenerLista(sql);
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
