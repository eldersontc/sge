package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.Cliente;
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

    public int ActualizarCliente(Cliente cliente) {
        String sql = String.format("UPDATE Ventas.Cliente SET razonSocial = '%s', tipoDocumentoIdentidad = '%s', "
                + "documentoIdentidad = '%s', nombreComercial = '%s', telefono = '%s', correo = '%s', idVendedor = %d, "
                + "nombreVendedor = '%s', idListaPrecioProducto = %d, nombreListaPrecioProducto = '%s', "
                + "idListaPrecioServicio = %d, nombreListaPrecioServicio = '%s', idListaPrecioMaquina = %d, "
                + "nombreListaPrecioMaquina = '%s', activo = %b WHERE idCliente = %d", cliente.getRazonSocial(), cliente.getTipoDocumentoIdentidad(), 
                cliente.getDocumentoIdentidad(), cliente.getNombreComercial(), cliente.getTelefono(), cliente.getCorreo(), cliente.getIdVendedor(), 
                cliente.getNombreVendedor(), cliente.getIdListaPrecioProducto(), cliente.getNombreListaPrecioProducto(), cliente.getIdListaPrecioServicio(), 
                cliente.getNombreListaPrecioServicio(), cliente.getIdListaPrecioMaquina(), cliente.getNombreListaPrecioMaquina(), cliente.isActivo(), cliente.getIdCliente());
        return super.Ejecutar(sql);
    }

    public int EliminarCliente(int idCliente) {
        String sql = "DELETE FROM Ventas.Cliente WHERE idCliente = " + idCliente;
        return super.Ejecutar(sql);
    }
}
