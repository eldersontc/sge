package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioMaquinaDAO extends BaseDAO {
    
    public int EliminarItemListaPrecioMaquina(int idItemListaPrecioMaquina) {
        String sql = "DELETE FROM Ventas.ItemListaPrecioMaquina WHERE idItemListaPrecioMaquina = " + idItemListaPrecioMaquina;
        return super.Ejecutar(sql);
    }
    
    public int EliminarItemListaPrecioMaquinaPorIdListaPrecioMaquina(int idListaPrecioMaquina) {
        String sql = "DELETE FROM Ventas.ItemListaPrecioMaquina WHERE idListaPrecioMaquina = " + idListaPrecioMaquina;
        return super.Ejecutar(sql);
    }
}
