package com.sge.modulos.inventarios.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoAlmacenDAO extends InventariosDAO {
    
    public List<Object[]> ObtenerProductoAlmacenesPorIdProducto(int idProducto) {
        String sql = "SELECT \n"
                + "ProductoAlmacen.idProductoAlmacen, Almacen.idAlmacen AS idAlmacen, Almacen.descripcion AS descripcionAlmacen, \n"
                + "ProductoAlmacen.stockFisico, ProductoAlmacen.stockComprometido, ProductoAlmacen.stockSolicitado, ProductoAlmacen.stockDisponible \n"
                + "FROM \n"
                + "Inventarios.ProductoAlmacen AS ProductoAlmacen INNER JOIN Inventarios.Almacen AS Almacen ON ProductoAlmacen.idAlmacen = Almacen.idAlmacen \n"
                + "WHERE ProductoAlmacen.idProducto = " + idProducto;
        return super.ObtenerLista(sql);
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
