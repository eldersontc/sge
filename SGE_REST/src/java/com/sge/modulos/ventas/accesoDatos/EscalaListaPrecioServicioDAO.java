package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioServicioDAO extends BaseDAO {
    
    public int ActualizarEscalaListaPrecioServicio(int idEscalaListaPrecioServicio, int desde, int hasta, double precio) {
        String sql = String.format("UPDATE Ventas.EscalaListaPrecioServicio SET desde = %d, hasta = %d, precio = %d WHERE idEscalaListaPrecioServicio = %d", desde, hasta, precio, idEscalaListaPrecioServicio);
        return super.Ejecutar(sql);
    }

    public int EliminarEscalaListaPrecioServicio(int idEscalaListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.EscalaListaPrecioServicio WHERE idEscalaListaPrecioServicio = " + idEscalaListaPrecioServicio;
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioServicioPorIdListaPrecioServicio(int idListaPrecioServicio) {
        String sql = String.format("DELETE FROM Ventas.EscalaListaPrecioServicio "
                + "WHERE idUnidadListaPrecioServicio IN "
                + "(SELECT idUnidadListaPrecioServicio FROM Ventas.UnidadListaPrecioServicio "
                + "WHERE idItemListaPrecioServicio IN "
                + "(SELECT idItemListaPrecioServicio FROM Ventas.ItemListaPrecioServicio WHERE idListaPrecioServicio = %d))", idListaPrecioServicio);
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioServicioPorIdItemListaPrecioServicio(int idItemListaPrecioServicio) {
        String sql = String.format("DELETE FROM Ventas.EscalaListaPrecioServicio "
                + "WHERE idUnidadListaPrecioServicio IN "
                + "(SELECT idUnidadListaPrecioServicio FROM Ventas.UnidadListaPrecioServicio "
                + "WHERE idItemListaPrecioServicio = %d)", idItemListaPrecioServicio);
        return super.Ejecutar(sql);
    }
    
    public int EliminarEscalaListaPrecioServicioPorIdUnidadListaPrecioServicio(int idUnidadListaPrecioServicio) {
        String sql = "DELETE FROM Ventas.EscalaListaPrecioServicio WHERE idUnidadListaPrecioServicio = " + idUnidadListaPrecioServicio;
        return super.Ejecutar(sql);
    }
}
