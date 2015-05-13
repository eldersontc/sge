package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemPresupuestoDAO extends BaseDAO {

    public int ActualizarItemPresupuesto(int idItemPresupuesto, double recargo, double total) {
        String sql = String.format("UPDATE Ventas.ItemPresupuesto SET recargo = %s, total = %s WHERE idItemPresupuesto = %d", recargo, total, idItemPresupuesto);
        return super.Ejecutar(sql);
    }

    public int EliminarItemPresupuesto(int idItemPresupuesto) {
        String sql = "DELETE FROM Ventas.ItemPresupuesto WHERE idItemPresupuesto = " + idItemPresupuesto;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemPresupuestoPorPresupuesto(int idPresupuesto) {
        String sql = "DELETE FROM Ventas.ItemPresupuesto WHERE idPresupuesto = " + idPresupuesto;
        return super.Ejecutar(sql);
    }
}
