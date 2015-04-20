package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoAlmacenDAO extends BaseDAO {
    
    public List<Object[]> ObtenerProductoAlmacenesPorIdProducto(int idProducto) {
        String sql = "SELECT \n"
                + "ProductoAlmacen.idProductoAlmacen, Almacen.idAlmacen AS idAlmacen, Almacen.descripcion AS descripcionAlmacen, \n"
                + "ProductoAlmacen.stockFisico, ProductoAlmacen.stockComprometido, ProductoAlmacen.stockSolicitado, ProductoAlmacen.stockDisponible \n"
                + "FROM \n"
                + "Inventarios.ProductoAlmacen AS ProductoAlmacen INNER JOIN Inventarios.Almacen AS Almacen ON ProductoAlmacen.idAlmacen = Almacen.idAlmacen \n"
                + "WHERE ProductoAlmacen.idProducto = " + idProducto;
        return super.ObtenerLista(sql);
    }
    
    public void ActualizarStockFisico(int idProducto, int idAlmacen, double cantidad){
        String sql = String.format("SELECT Inventarios.Sp_Actualizar_Stock_Fisico(%d, %d, %s)", idProducto, idAlmacen, cantidad);
        super.EjecutarFuncion(sql);
    }
    
    public int EliminarProductoAlmacen(int idProductoAlmacen) {
        String sql = "DELETE FROM Inventarios.ProductoAlmacen WHERE idProductoAlmacen = " + idProductoAlmacen;
        return super.Ejecutar(sql);
    }
    
    public int EliminarProductoAlmacenPorIdProducto(int idProducto) {
        String sql = "DELETE FROM Inventarios.ProductoAlmacen WHERE idProducto = " + idProducto;
        return super.Ejecutar(sql);
    }
}
