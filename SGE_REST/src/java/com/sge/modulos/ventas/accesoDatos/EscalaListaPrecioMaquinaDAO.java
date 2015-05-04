package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioMaquinaDAO extends BaseDAO {
    
    public int ActualizarEscalaListaPrecioMaquina(int idEscalaListaPrecioMaquina, int desde, int hasta, double precio) {
        String sql = String.format("UPDATE Ventas.EscalaListaPrecioMaquina SET desde = %d, hasta = %d, precio = %s WHERE idEscalaListaPrecioMaquina = %d", desde, hasta, precio, idEscalaListaPrecioMaquina);
        return super.Ejecutar(sql);
    }

    public int EliminarEscalaListaPrecioMaquina(int idEscalaListaPrecioMaquina) {
        String sql = "DELETE FROM Ventas.EscalaListaPrecioMaquina WHERE idEscalaListaPrecioMaquina = " + idEscalaListaPrecioMaquina;
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioMaquinaPorIdListaPrecioMaquina(int idListaPrecioMaquina) {
        String sql = String.format("DELETE FROM Ventas.EscalaListaPrecioMaquina "
                + "WHERE idItemListaPrecioMaquina IN "
                + "(SELECT idItemListaPrecioMaquina FROM Ventas.ItemListaPrecioMaquina "
                + "WHERE idListaPrecioMaquina = %d)", idListaPrecioMaquina);
        return super.Ejecutar(sql);
    }

    public int EliminarEscalaListaPrecioMaquinaPorIdItemListaPrecioMaquina(int idItemListaPrecioMaquina) {
        String sql = "DELETE FROM Ventas.EscalaListaPrecioMaquina WHERE idItemListaPrecioMaquina = " + idItemListaPrecioMaquina;
        return super.Ejecutar(sql);
    }
}
