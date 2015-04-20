package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DireccionClienteDAO extends BaseDAO {

    public List<Object[]> ObtenerDireccionesClientePorIdCliente(int idCliente) {
        String sql = "SELECT \n"
                + "DireccionCliente.idDireccionCliente, DireccionCliente.idDepartamento, Departamento.nombre AS departamento, DireccionCliente.idProvincia, Provincia.nombre AS provincia, DireccionCliente.idDistrito, Distrito.nombre AS distrito, DireccionCliente.direccion \n"
                + "FROM \n"
                + "Ventas.DireccionCliente AS DireccionCliente \n"
                + "INNER JOIN Administracion.Departamento AS Departamento ON Departamento.idDepartamento = DireccionCliente.idDepartamento \n"
                + "INNER JOIN Administracion.Provincia AS Provincia ON Provincia.idProvincia = DireccionCliente.idProvincia \n"
                + "INNER JOIN Administracion.Distrito AS Distrito ON Distrito.idDistrito = DireccionCliente.idDistrito \n"
                + "WHERE DireccionCliente.idCliente = " + idCliente;
        return super.ObtenerLista(sql);
    }

    public int ActualizarDireccionCliente(int idDireccionCliente, int idDepartamento, int idProvincia, int idDistrito, String direccion) {
        String sql = String.format("UPDATE Ventas.DireccionCliente SET idDepartamento = %d, idProvincia = %d, idDistrito = %d, direccion = '%s' WHERE idDireccionCliente = %d", idDepartamento, idProvincia, idDistrito, direccion, idDireccionCliente);
        return super.Ejecutar(sql);
    }

    public int EliminarDireccionCliente(int idDireccionCliente) {
        String sql = "DELETE FROM Ventas.DireccionCliente WHERE idDireccionCliente = " + idDireccionCliente;
        return super.Ejecutar(sql);
    }
    
    public int EliminarDireccionClientePorIdCliente(int idCliente) {
        String sql = "DELETE FROM Ventas.DireccionCliente WHERE idCliente = " + idCliente;
        return super.Ejecutar(sql);
    }
}
