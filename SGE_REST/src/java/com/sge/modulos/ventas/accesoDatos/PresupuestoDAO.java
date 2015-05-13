package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PresupuestoDAO extends BaseDAO {

    public List<Object[]> ObtenerPresupuestos(String filtro) {
        String sql = "SELECT \n"
                + "Presupuesto.idPresupuesto, Presupuesto.numero, Presupuesto.fechaCreacion, Presupuesto.razonSocialCliente, Presupuesto.total \n"
                + "FROM \n"
                + "Ventas.Presupuesto " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarPresupuesto(int idPresupuesto, int idCliente, String razonSocialCliente, int idMoneda, String simboloMoneda, String numeroOrdenCompra, String instrucciones, double total) {
        String sql = String.format("UPDATE Ventas.Presupuesto SET idCliente = %d, razonSocialCliente = '%s',idMoneda = %d, simboloMoneda = '%s', numeroOrdenCompra = '%s', instrucciones = '%s', total = %s WHERE idPresupuesto = %d", idCliente, razonSocialCliente, idMoneda, simboloMoneda, numeroOrdenCompra, instrucciones, total, idPresupuesto);
        return super.Ejecutar(sql);
    }

    public int EliminarPresupuesto(int idPresupuesto) {
        String sql = "DELETE FROM Ventas.Presupuesto WHERE idPresupuesto = " + idPresupuesto;
        return super.Ejecutar(sql);
    }
}
