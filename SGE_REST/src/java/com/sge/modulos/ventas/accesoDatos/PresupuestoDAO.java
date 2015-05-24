package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.Presupuesto;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PresupuestoDAO extends BaseDAO {

    public List<Presupuesto> ObtenerPresupuestos(String filtro) {
        String sql = "SELECT \n"
                + "Presupuesto.* \n"
                + "FROM \n"
                + "Ventas.Presupuesto AS Presupuesto " + filtro;
        return super.ObtenerLista(sql, Presupuesto.class);
    }

    public int ActualizarPresupuesto(int idPresupuesto, int idCliente, String razonSocialCliente, int idMoneda, String simboloMoneda, String numeroOrdenCompra, String instrucciones, double total) {
        String sql = String.format("UPDATE Ventas.Presupuesto SET idCliente = %d, razonSocialCliente = '%s',idMoneda = %d, simboloMoneda = '%s', numeroOrdenCompra = '%s', instrucciones = '%s', total = %s WHERE idPresupuesto = %d", idCliente, razonSocialCliente, idMoneda, simboloMoneda, numeroOrdenCompra, instrucciones, total, idPresupuesto);
        return super.Ejecutar(sql);
    }

    public int EliminarPresupuesto(int idPresupuesto) {
        String sql = "DELETE FROM Ventas.Presupuesto WHERE idPresupuesto = " + idPresupuesto;
        return super.Ejecutar(sql);
    }
    
    public int ActualizarEstadoPresupuesto(int idPresupuesto, String estado) {
        String sql = String.format("UPDATE Ventas.Presupuesto SET estado = '%s' WHERE idPresupuesto = %d", estado, idPresupuesto);
        return super.Ejecutar(sql);
    }
}
