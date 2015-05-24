package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.DireccionCliente;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DireccionClienteDAO extends BaseDAO {

    public List<DireccionCliente> ObtenerDireccionesCliente(String filtro) {
        String sql = "SELECT \n"
                + "DireccionCliente.* \n"
                + "FROM \n"
                + "Ventas.DireccionCliente AS DireccionCliente " + filtro;
        return super.ObtenerLista(sql, DireccionCliente.class);
    }

    public int ActualizarDireccionCliente(int idDireccionCliente, int idDepartamento, String nombreDepartamento, int idProvincia, String nombreProvincia, int idDistrito, String nombreDistrito, String direccion) {
        String sql = String.format("UPDATE Ventas.DireccionCliente SET idDepartamento = %d, nombreDepartamento = '%s', idProvincia = %d, nombreProvincia = %s, idDistrito = %d, nombreDistrito = '%s', direccion = '%s' WHERE idDireccionCliente = %d", idDepartamento, nombreDepartamento, idProvincia, nombreProvincia, idDistrito, nombreDistrito, direccion, idDireccionCliente);
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
