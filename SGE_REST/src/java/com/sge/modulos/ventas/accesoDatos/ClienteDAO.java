package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ClienteDAO extends BaseDAO {

    public List<Object[]> ObtenerClientes(String filtro) {
        String sql = "SELECT \n"
                + "Cliente.idCliente, Cliente.razonSocial, Cliente.tipoDocumentoIdentidad, Cliente.documentoIdentidad, Cliente.nombreComercial, Cliente.fechaUltimaVenta, Cliente.activo \n"
                + "FROM \n"
                + "Ventas.Cliente " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarCliente(int idCliente, String razonSocial, String tipoDocumentoIdentidad, String documentoIdentidad, String nombreComercial, String telefono, String correo, int idVendedor, String nombreVendedor, boolean activo) {
        String sql = String.format("UPDATE Ventas.Cliente SET razonSocial = '%s', tipoDocumentoIdentidad = '%s', documentoIdentidad = '%s', nombreComercial = '%s', telefono = '%s', correo = '%s', idVendedor = %d, nombreVendedor = '%s', activo = %b WHERE idCliente = %d", razonSocial, tipoDocumentoIdentidad, documentoIdentidad, nombreComercial, telefono, correo, idVendedor, nombreVendedor, activo, idCliente);
        return super.Ejecutar(sql);
    }

    public int EliminarCliente(int idCliente) {
        String sql = "DELETE FROM Ventas.Cliente WHERE idCliente = " + idCliente;
        return super.Ejecutar(sql);
    }
}
