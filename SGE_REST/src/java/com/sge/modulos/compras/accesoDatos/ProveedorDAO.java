package com.sge.modulos.compras.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.compras.entidades.Proveedor;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProveedorDAO extends BaseDAO {

    public List<Proveedor> ObtenerProveedores(String filtro) {
        String sql = "SELECT \n"
                + "Proveedor.* \n"
                + "FROM \n"
                + "Compras.Proveedor AS Proveedor " + filtro;
        return super.ObtenerLista(sql, Proveedor.class);
    }

    public int ActualizarProveedor(int idProveedor, String razonSocial, String tipoDocumentoIdentidad, String documentoIdentidad, String nombreComercial, boolean activo) {
        String sql = String.format("UPDATE Compras.Proveedor SET razonSocial = '%s', tipoDocumentoIdentidad = '%s', documentoIdentidad = '%s', nombreComercial = '%s', activo = %b WHERE idProveedor = %d", razonSocial, tipoDocumentoIdentidad, documentoIdentidad, nombreComercial, activo, idProveedor);
        return super.Ejecutar(sql);
    }

    public int EliminarProveedor(int idProveedor) {
        String sql = "DELETE FROM Compras.Proveedor WHERE idProveedor = " + idProveedor;
        return super.Ejecutar(sql);
    }
}
