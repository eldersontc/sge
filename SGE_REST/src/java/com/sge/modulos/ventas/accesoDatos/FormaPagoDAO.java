package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class FormaPagoDAO extends BaseDAO {

    public List<Object[]> ObtenerFormasPago(String filtro) {
        String sql = "SELECT \n"
                + "FormaPago.idFormaPago, FormaPago.descripcion, FormaPago.credito, FormaPago.dias, FormaPago.activo \n"
                + "FROM \n"
                + "Ventas.FormaPago " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarFormaPago(int idFormaPago, String descripcion, boolean credito, int dias, boolean activo) {
        String sql = String.format("UPDATE Ventas.FormaPago SET descripcion = '%s', credito = %b, dias = %d, activo = %b WHERE idFormaPago = %d", descripcion, credito, dias, activo, idFormaPago);
        return super.Ejecutar(sql);
    }

    public int EliminarFormaPago(int idFormaPago) {
        String sql = "DELETE FROM Ventas.FormaPago WHERE idFormaPago = " + idFormaPago;
        return super.Ejecutar(sql);
    }
}
