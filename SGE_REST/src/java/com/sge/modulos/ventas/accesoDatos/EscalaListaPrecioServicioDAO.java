package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioServicio;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioServicioDAO extends BaseDAO {
    
    public List<EscalaListaPrecioServicio> ObtenerEscalasPorUnidad(int idListaPrecioServicio, int idServicio, int idServicioUnidad){
        String sql = String.format("SELECT \n" +
                                    "	EscalaListaPrecioServicio.* \n" +
                                    "FROM \n" +
                                    "	Ventas.ListaPrecioServicio AS ListaPrecioServicio  \n" +
                                    "	INNER JOIN Ventas.ItemListaPrecioServicio AS ItemListaPrecioServicio \n" +
                                    "	ON ListaPrecioServicio.idListaPrecioServicio = ItemListaPrecioServicio.idListaPrecioServicio\n" +
                                    "	INNER JOIN Ventas.UnidadListaPrecioServicio AS UnidadListaPrecioServicio \n" +
                                    "	ON ItemListaPrecioServicio.idItemListaPrecioServicio = UnidadListaPrecioServicio.idItemListaPrecioServicio\n" +
                                    "	INNER JOIN Ventas.EscalaListaPrecioServicio AS EscalaListaPrecioServicio \n" +
                                    "	ON UnidadListaPrecioServicio.idUnidadListaPrecioServicio =  EscalaListaPrecioServicio.idUnidadListaPrecioServicio\n" +
                                    "WHERE \n" +
                                    "	ListaPrecioServicio.idListaPrecioServicio = %d AND ItemListaPrecioServicio.idServicio = %d \n" +
                                    "	AND UnidadListaPrecioServicio.idServicioUnidad = %d", idListaPrecioServicio, idServicio, idServicioUnidad);
        return super.ObtenerLista(sql, EscalaListaPrecioServicio.class);
    }
    
    public int ActualizarEscalaListaPrecioServicio(int idEscalaListaPrecioServicio, int desde, int hasta, double precio) {
        String sql = String.format("UPDATE Ventas.EscalaListaPrecioServicio SET desde = %d, hasta = %d, precio = %s WHERE idEscalaListaPrecioServicio = %d", desde, hasta, precio, idEscalaListaPrecioServicio);
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
