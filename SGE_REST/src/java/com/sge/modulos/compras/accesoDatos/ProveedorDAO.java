package com.sge.modulos.compras.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class ProveedorDAO extends ComprasDAO {

    public List<Object[]> ObtenerProveedores() {
        String sql = "SELECT \n"
                + "Proveedor.idProveedor, Proveedor.razonSocial, Proveedor.tipoDocumentoIdentidad, Proveedor.documentoIdentidad, Proveedor.nombreComercial, Proveedor.fechaUltimaCompra, Proveedor.activo \n"
                + "FROM \n"
                + "Compras.Proveedor \n";
        return super.ObtenerLista(sql);
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
